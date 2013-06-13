package de.skiptag.js.performance;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

import de.skiptag.js.performance.dynjs.DynJsRunner;
import de.skiptag.js.performance.rhino.RhinoRunner;

public class PerformanceExample {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		List<Long> dynjs = Lists.newArrayList();
		List<Long> rhino = Lists.newArrayList();

		{
			Stopwatch stopwatch = new Stopwatch();
			for (int i = 0; i < 100; i++) {
				RhinoRunner rhinoRunner = new RhinoRunner("main.js");
				stopwatch.start();
				rhinoRunner.start();
				stopwatch.stop();
				rhino.add(stopwatch.elapsed(TimeUnit.MILLISECONDS));
				System.out.println("Rhino Execution took: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
			}
		}
		{
			Stopwatch stopwatch = new Stopwatch();
			for (int i = 0; i < 100; i++) {
				DynJsRunner runner = new DynJsRunner("main.js");
				stopwatch.start();
				runner.start();
				stopwatch.stop();
				dynjs.add(stopwatch.elapsed(TimeUnit.MILLISECONDS));
				System.out.println("DynJS Execution took: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
			}
		}
		System.out.println("Average Time DynJS: " + calculateAvg(dynjs));
		System.out.println("Average Time Rhino: " + calculateAvg(rhino));
	}

	private static double calculateAvg(List<Long> dynjs) {
		double result = 0.0;
		for (Long val : dynjs) {
			result += val.longValue();
		}
		return result / dynjs.size();
	}
}
