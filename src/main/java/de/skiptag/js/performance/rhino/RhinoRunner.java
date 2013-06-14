package de.skiptag.js.performance.rhino;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.commonjs.module.Require;
import org.mozilla.javascript.commonjs.module.RequireBuilder;

public class RhinoRunner {
	private ResourceLoader resourceLoader;

	public static ThreadLocal<ScriptableObject> scope = new ThreadLocal<>();

	private String scriptName;

	public RhinoRunner(String scriptName) {
		this.resourceLoader = new ResourceLoader();
		this.scriptName = scriptName;
	}

	// Support for loading from CommonJS modules
	private Require installRequire(Context cx, ScriptableObject scope) {
		RequireBuilder rb = new RequireBuilder();
		rb.setSandboxed(false);
		rb.setModuleScriptProvider(new ModuleScriptProvider(resourceLoader));

		Require require = rb.createRequire(cx, scope);
		return require;
	}

	@SuppressWarnings("unused")
	public void start() throws Exception {
		Context cx = Context.enter();
		cx.setOptimizationLevel(2);
		try {
			scope.set(cx.initStandardObjects());

			Require require = installRequire(cx, scope.get());
			Scriptable script = require.requireMain(cx, scriptName);

		} finally {
			Context.exit();
		}
	}

	public static ScriptableObject getScope() {
		return scope.get();
	}
}