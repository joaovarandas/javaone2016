package br.com.joaovarandas.nashorn;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.script.ScriptEngineManager;

public class NashornScriptFactory {

	private static final NashornScriptFactory instance = new NashornScriptFactory();

	public static NashornScriptFactory getInstance() {
		return instance;
	}
	
	private final Map<String, Object> data = new LinkedHashMap<>();

	public final Object invoke(final String path, final String functionName, final Object args) {
		try {
			final Map<String, Object> exports = new LinkedHashMap<>();
			final javax.script.ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
			
			final javax.script.Bindings b = scriptEngine.getContext().getBindings(javax.script.ScriptContext.ENGINE_SCOPE);			
			b.put("storage", data);
			b.put("exports", exports);

			String homeDir = System.getProperty("user.home");			
			scriptEngine.eval("load('" + homeDir + "/nashorn-js/" + path + ".js');");
			
			@SuppressWarnings("restriction")
			jdk.nashorn.api.scripting.ScriptObjectMirror exported = 
				(jdk.nashorn.api.scripting.ScriptObjectMirror ) exports.get(functionName);
			
			return exported.call(exports, args);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);

		}
	}
	

	

}
