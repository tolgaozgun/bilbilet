// package edu.bilkent.bilbilet.controller;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import edu.bilkent.bilbilet.exception.ExampleException;
// import edu.bilkent.bilbilet.response.Response;
// import edu.bilkent.bilbilet.service.IExampleService;
// import lombok.AllArgsConstructor;

// @AllArgsConstructor
// @RestController
// @RequestMapping("api")
// public class ExampleController {
//     private final IExampleService exampleService;

//     @GetMapping("hello")
//     public ResponseEntity<Object> refreshToken() {
//         try {
//             return Response.create("ok", HttpStatus.OK, "hello world");
//         } catch (Exception e) {
//             return null;
//         }        
//     }

//     // This example endpoints checks (not actually) if a given string is a number and is in the database
//     @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
//     @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "endpoint/{input}")
//     public ResponseEntity<Object> exampleEndpoint(@PathVariable("input") String input) {
//         try {
//             String msg = exampleService.numExists(input) ? "Number exists." : "Number does not exist.";
//             return Response.create(msg, HttpStatus.OK);
//         }
//         catch (ExampleException e1) {
//             return Response.create(e1.getMessage(), HttpStatus.BAD_REQUEST);  
//         }
//         catch (Exception e2) {
//             return Response.create("An error has occurred.", HttpStatus.INTERNAL_SERVER_ERROR);  
//         }       
//     }
// }
