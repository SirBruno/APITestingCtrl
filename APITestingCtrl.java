public class APITestingCtrl {

    // get method that will be called on a VF page
    public static Map<Object,Object> getData() {

        Http http = new Http();
        HttpRequest request = new HttpRequest();
        request.setEndpoint('https://jsonplaceholder.typicode.com/posts');
        request.setMethod('GET');
        HttpResponse response = http.send(request);

        // if the request is successful, return the data
        if (response.getStatusCode() == 200) {

            // map that will hold the values
            // the first Object is the "Id" of the JSON (a unique value)
            // the second Object is the data itself (id, title, body, etc.)
            Map<Object,Object> dataMap = new Map<Object,Object>();

            // deserialize the JSON response
            List<Object> results = (List<Object>) JSON.deserializeUntyped(response.getBody());

            // iterate through the JSON's data
            for(Object r : results){

              // turn each result into a map (id=1,title="lorem ipsum", etc.)
            	Map<String,Object> data = (Map<String,Object>)r;

              // pass data into the map
              // first, the unique key, which is the "Id" returning from the request (id=1, id=2, etc.)
              // second, the value, which is the Object itself (id=1,title="lorem ipsum", etc.)
				      dataMap.put(data.get('id'), r);
            }
            
            // return the map with all the data returned from the request
            return dataMap;

        // in case the request was no successful, return null
        } else return null;
    }
}
