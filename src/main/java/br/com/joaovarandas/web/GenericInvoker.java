package br.com.joaovarandas.web;

import java.util.Map;

import javax.script.ScriptException;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaovarandas.nashorn.NashornScriptFactory;

@RestController 
@RequestMapping(value = "/invoker", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenericInvoker {
	
	@RequestMapping(value = "/{fileName}", method=RequestMethod.GET)
    public Object doGet(@PathVariable("fileName") String fileName, @RequestParam Map<String, String> params) throws NoSuchMethodException, ScriptException {
		String functionName = (String) params.get("fn");
		params.remove("fn");
		
        return NashornScriptFactory.getInstance().invoke(fileName, functionName, params);
    }
	
	@RequestMapping(value = "/{fileName}", method=RequestMethod.POST)
    public Object doPost(@PathVariable("fileName") String fileName, @RequestBody Map<String, Object> params) throws NoSuchMethodException, ScriptException {
		String functionName = (String) params.get("fn");
		Object args = params.get("data");
		
        return NashornScriptFactory.getInstance().invoke(fileName, functionName, args);
    }
	
	
	
}
