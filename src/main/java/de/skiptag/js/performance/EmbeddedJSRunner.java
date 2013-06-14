package de.skiptag.js.performance;

import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.dynjs.Config;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import de.skiptag.js.performance.dynjs.DynJsRunner;

public class EmbeddedJSRunner {
	private String script;
	private ScriptEngine runtime;

	public EmbeddedJSRunner(String scriptName) throws Exception {
		Config config = new Config();
		ScriptEngineManager mgr = new ScriptEngineManager();
		runtime = mgr.getEngineByName("JavaScript");

		script = CharStreams.toString(new InputStreamReader(DynJsRunner.class.getClassLoader().getResourceAsStream(
				scriptName), Charsets.UTF_8));

	}

	public void start() throws Exception {
		runtime.eval(script);
	}
}
