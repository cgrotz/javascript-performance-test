package de.skiptag.js.performance.dynjs;

import java.io.InputStreamReader;

import org.dynjs.Config;
import org.dynjs.runtime.DynJS;
import org.dynjs.runtime.GlobalObject;
import org.dynjs.runtime.GlobalObjectFactory;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import de.skiptag.js.performance.Console;

public class DynJsRunner {
	private String scriptName;
	private DynJS runtime;

	public DynJsRunner(String scriptName) {
		this.scriptName = scriptName;
		Config config = new Config();
		config.setGlobalObjectFactory(getGlobalObjectFactory());
		runtime = new DynJS(config);
	}

	public void start() throws Exception {
		String script = CharStreams.toString(new InputStreamReader(DynJsRunner.class.getClassLoader().getResourceAsStream(
				scriptName), Charsets.UTF_8));
		runtime.evaluate(script);
	}

	private GlobalObjectFactory getGlobalObjectFactory() {
		return new GlobalObjectFactory() {

			@Override
			public GlobalObject newGlobalObject(DynJS runtime) {
				GlobalObject globalObject = new GlobalObject(runtime);
				globalObject.defineGlobalProperty("console", new Console());
				globalObject.defineGlobalProperty("setTimeout", new SetTimeout(globalObject));
				return globalObject;
			}
		};
	}
}