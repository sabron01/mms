/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//var wsUri = "ws://" + document.location.host + document.location.pathname + "mediatorendpoint";
(function($) {
	var wsUri = "ws://localhost:8080/mms/mediatorendpoint";
	var websocket = new WebSocket(wsUri);

	websocket.onerror = function(evt) {
		onError(evt)
	};

	function onError(evt) {
		writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
	}

	// For testing purposes
	var output = $("#output");
	websocket.onopen = function(evt) {
		onOpen(evt)
	};

	function writeToScreen(message) {
		output.html(message + "<br>");
	}

	function onOpen() {
		writeToScreen("Connected to " + wsUri);
	}
	// End test functions

	websocket.onmessage = function(evt) {
		onMessage(evt)
	};

	function sendText(json) {
		console.log("sending text: " + json);
		websocket.send(json);
	}

	function onMessage(evt) {
		console.log("received: " + evt.data);
		var json = JSON.parse(event.data);
		console.log(json.type)
		if (json.type == "Task") {
			var last_count = $("#new-task-count").html();
			$("#new-task-count").html(last_count + 1);
			$("#tasks")
					.prepend(
							'<li><a href="#"><h3>'
									+ json.text
									+ '<small class="pull-right">20%</small></h3><div class="progress xs"><div class="progress-bar progress-bar-aqua"style="width: 20%" role="progressbar" aria-valuenow="20"aria-valuemin="0" aria-valuemax="100"><span class="sr-only">10% Complete</span></div></div></a></li>');
		}
		if (json.type == "Notification") {
			var last_count = $("#new-notif-count").html();
			$("#new-notif-count").html(last_count + 1);
			$("#notifications").prepend(
					'<li><a href="#"> <i class="ion ion-ios7-people info"></i>'
							+ json.text + '</a></li>');
		}
		if (json.type == "Message") {
			var last_count = $("#new-message-count").html();
			var new_count = +last_count + 1
			$("#new-message-count").html(new_count);
			$("#messagesCount").html(new_count);			
			$("#messages")
					.prepend(
							'<a href="#"><div class="pull-left"><img src="/mms/javax.faces.resource/img/avatar3.png" class="img-circle" alt="User Image" /></div><h4>'
									+ json.sender
									+ '<small><i class="fa fa-clock-o"></i>'
									+ json.sendTime
									+ '</small></h4><p>'
									+ json.text + '</p></a>');
		}
		if (json.type = "Alert") {

		}

	}

})(jQuery);
