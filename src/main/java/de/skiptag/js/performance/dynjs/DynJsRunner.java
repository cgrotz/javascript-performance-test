package de.skiptag.js.performance.dynjs;

import java.io.InputStreamReader;

import org.dynjs.Config;
import org.dynjs.runtime.DynJS;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

public class DynJsRunner {
	private String scriptName;
	private DynJS runtime;

	public DynJsRunner(String scriptName) {
		this.scriptName = scriptName;
		Config config = new Config();
		runtime = new DynJS(config);
	}

	public void start() throws Exception {
		String htmlrunner = CharStreams.toString(new InputStreamReader(DynJsRunner.class.getClassLoader()
				.getResourceAsStream("dromaeo/htmlrunner.js"), Charsets.UTF_8));

		String script = CharStreams.toString(new InputStreamReader(DynJsRunner.class.getClassLoader()
				.getResourceAsStream(scriptName), Charsets.UTF_8));
		runtime.evaluate(htmlrunner + "\n" + script);
	}
}