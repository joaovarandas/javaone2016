package br.com.joaovarandas.web;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping(value = "/simple", produces = MediaType.APPLICATION_JSON_VALUE)
public class SimpleInvoker {
	
	@RequestMapping(value = "/hello", method=RequestMethod.GET)
    public Object helloWorld() throws NoSuchMethodException, ScriptException {

		javax.script.ScriptEngineManager scriptManager = new ScriptEngineManager();
		javax.script.ScriptEngine scriptEngine = scriptManager.getEngineByName("nashorn");
		
		// Object obj = scriptEngine.eval("'Hello World!'");
		
		// Object obj = scriptEngine.eval("5+17/3");
		
		Object obj = scriptEngine.eval("var obj = { 'message': 'Hello World' }; obj;");
		
		return obj;

	}
	
	
	
	
}
