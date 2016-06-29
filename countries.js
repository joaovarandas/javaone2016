(function(scope, exports, storage) {

	if (storage["countries"] == null) storage["countries"] = [];
	var countries = storage["countries"];

	function validate(obj) {
		print("validating: " + obj);

		return true;
	}

	function insert(obj) {
		validate(obj);
		countries.push(obj);

		return { "message": "Success!" };
	}

	function remove(obj) {
		countries.splice(obj["index"], 1);

		return { "message": "Success!" };
	}

	function find() {
		return countries; //Java.to(countries, java.util.List);
	}
	
function helloWorld() {
	return " Hwllo World!";
}

	exports["insert"] = insert;	
	exports["remove"] = remove;	
	exports["find"] = find;	
	

	exports["hello"] = helloWorld;	

	
})({}, exports, storage);