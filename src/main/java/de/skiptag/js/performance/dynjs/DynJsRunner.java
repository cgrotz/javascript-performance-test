package de.skiptag.js.performance.dynjs;

import java.io.InputStreamReader;

import org.dynjs.Config;
import org.dynjs.runtime.DynJS;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

public class DynJsRunner {
	private String scriptName;
	private DynJS runtime;
	private String script;

	public DynJsRunner(String scriptName) throws Exception {
		this.scriptName = scriptName;
		Config config = new Config();
		runtime = new DynJS(config);
		script = CharStreams.toString(new InputStreamReader(DynJsRunner.class.getClassLoader().getResourceAsStream(
				scriptName), Charsets.UTF_8));

	}

	public void start() throws Exception {
		runtime.evaluate(script);
	}
}