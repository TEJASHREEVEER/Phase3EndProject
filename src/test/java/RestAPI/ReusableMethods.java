package RestAPI;

	import java.util.HashMap;
	import io.restassured.RestAssured;
	import io.restassured.http.ContentType;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;

	public class ReusableMethods {

		public static String apiPath = "http://localhost:8088/employees/";
		public static int numberOfEmployees;
		public static String employeeName;

		public Response getEmployee() {
			RestAssured.baseURI = apiPath;
			RequestSpecification request = RestAssured.given();
			Response response = request.get();
			String body = response.getBody().asString();
			System.out.println("Response body is " + body);
			System.out.println("Response code is " + response.statusCode());
			System.out.println("Response header are  " + response.getHeader("Content-Type"));
			numberOfEmployees = response.jsonPath().getList("employees").size();
			
			return response;

		}

		public Response createNewEmployee(String name, String lastName, String salary, String email) {
			HashMap<String, Object> requestBody = new HashMap<String, Object>();
			requestBody.put("firstName", name);
			requestBody.put("lastName", lastName);
			requestBody.put("salary", salary);
			requestBody.put("email", email);
			RestAssured.baseURI = apiPath;
			RequestSpecification request = RestAssured.given();
			Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(requestBody).post();
			String body = response.getBody().asString();
			System.out.println("The response body is : " + body);
			System.out.println("Response code is " + response.statusCode());

			employeeName = response.jsonPath().getString("firstName");

			return response;

		}

		public Response updateEmployee(String id) {
			HashMap<String, Object> requestBody = new HashMap<String, Object>();
			requestBody.put("firstName", "Tom");
			requestBody.put("lastName", "Dae");
			requestBody.put("salary", "100000");
			requestBody.put("email", "an@gmail.com");

			RestAssured.baseURI = apiPath;
			RequestSpecification request = RestAssured.given();

			Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(requestBody).put(id);

			String body = response.getBody().asString();
			System.out.println("Response body is " + body);
			System.out.println("Response code is " + response.statusCode());

			employeeName = response.jsonPath().getString("firstName");

			return response;
		}

		public Response getEmployee(String empid) {
			RestAssured.baseURI = apiPath;
			RequestSpecification request = RestAssured.given();
			Response response = request.get(empid);
			String body = response.getBody().asString();
			System.out.println("Response body is " + body);
			System.out.println("Response code is " + response.statusCode());
			System.out.println("Response header are  " + response.getHeader("Content-Type"));
			employeeName = response.jsonPath().getString("firstName");
			return response;

		}

		public Response deleteEmployee(String empId) {
			RestAssured.baseURI = apiPath;
			RequestSpecification request = RestAssured.given();
			Response response = request.delete(empId);
			String body = response.getBody().asString();
			System.out.println("Response body is " + body);
			System.out.println("Response code is " + response.statusCode());
			return response;
		}

	}


