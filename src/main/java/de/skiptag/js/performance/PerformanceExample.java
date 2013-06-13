package de.skiptag.js.performance;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import de.skiptag.js.performance.dynjs.DynJsRunner;
import de.skiptag.js.performance.rhino.RhinoRunner;

public class PerformanceExample {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		URL url = PerformanceExample.class.getClassLoader().getResource("dromaeo/tests");
		File folder = new File(url.toURI());
		System.out.println("File\tRhino\tDynJS");

		for (String file : folder.list(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.endsWith(".js");
			}
		})) {
			System.out.print(file);
			runOnRhino("dromaeo/tests/" + file);
			runOnDynJS("dromaeo/tests/" + file);
			System.out.println("");
		}
	}

	private static void runOnDynJS(String scriptName) {
		try {
			Stopwatch stopwatch = new Stopwatch();
			DynJsRunner runner = new DynJsRunner(scriptName);
			stopwatch.start();
			runner.start();
			stopwatch.stop();
			System.out.print("\t" + stopwatch.elapsed(TimeUnit.MILLISECONDS));
		} catch (Exception e) {
			System.out.println("\tError");
		}
	}

	private static void runOnRhino(String scriptName) throws Exception {
		try {
			Stopwatch stopwatch = new Stopwatch();
			RhinoRunner rhinoRunner = new RhinoRunner(scriptName);
			stopwatch.start();
			rhinoRunner.start();
			stopwatch.stop();
			System.out.print("\t" + stopwatch.elapsed(TimeUnit.MILLISECONDS));
		} catch (Exception e) {
			System.out.println("\tError");
		}
	}
}
