package com.idb.messagemq.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idb.messagemq.Models.Employee;
import com.idb.messagemq.Models.NotificationRabbitMQModel;
import com.idb.messagemq.Services.RabbitMQSender;

@RestController
@RequestMapping(value = "/api/v1/test/rabbitmq")
public class RabbitMQWebController {

	@Autowired
	RabbitMQSender rabbitMQSender;

	// @GetMapping(value = "/producer")
	// public String producer(@RequestParam("empName") String empName,@RequestParam("empId") String empId) {
	
    //     Employee emp=new Employee();
    //     emp.setEmpId(empId);
    //     emp.setEmpName(empName);
	// 	rabbitMQSender.send(emp);

	// 	return "Message sent to the RabbitMQ JavaInUse Successfully";
	// }

    @PostMapping(
        value = "/producer-stable",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<Object> producerStable(@RequestBody NotificationRabbitMQModel message) {
	
        // NotificationRabbitMQModel message = new NotificationRabbitMQModel("6507142651538755728,4921452259975351442", "https://google.com", "This is content!", "This is title", "", "", "");

        rabbitMQSender.sendZalo(message);

		return new ResponseEntity<>("{ \"Notice\": \"Send OK!\" }", HttpStatus.OK);
	}

    // @GetMapping(value = "/zzz")
    // public String receive() {
    //     String tmp = rabbitMQSender.receive();

    //     return tmp;
    // }

    // @GetMapping(
    //     value = "/yyy",
    //     produces = MediaType.APPLICATION_JSON_VALUE
    // )
    // public ResponseEntity<Object> receiveAndConvert() {
    //     ResponseEntity<Object> entity;

    //     Employee tmp = rabbitMQSender.receiveAndConvertToPojo();

    //     entity = new ResponseEntity<>(tmp, HttpStatus.OK);

    //     return entity;
    // }

}
