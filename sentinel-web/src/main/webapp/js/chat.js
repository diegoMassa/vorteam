var accessToken = "954024d1ea8141ef8ffe28bec9fbf1e3",
  baseUrl = "https://api.api.ai/v1/query?v=20150910",
		  
  $speechInput,
  $recBtn,
  recognition,
  messageRecording = "Escuchándolo hablar...",
  messageCouldntHear = "No pude escucharlo. Puede por favor intentarlo nuevamente?",
  messageInternalError = "Oh no, hubo un problema conmigo en el servidor. Notificaré a mis creadores",
  messageSorry = "Lo siento, no tengo una respuesta para eso. Lo reportaré para aprender y darle una respuesta próximamente";

$(document).on('click', '.panel-heading span.icon_minim', function (e) {
    var $this = $(this);
    if (!$this.hasClass('panel-collapsed')) {
        $this.parents('.panel').find('.panel-body').slideUp();
        $this.addClass('panel-collapsed');
        $this.removeClass('glyphicon-minus').addClass('glyphicon-plus');
    } else {
        $this.parents('.panel').find('.panel-body').slideDown();
        $this.removeClass('panel-collapsed');
        $this.removeClass('glyphicon-plus').addClass('glyphicon-minus');
    }
});

$(document).on('focus', '.panel-footer input.chat_input', function (e) {
    var $this = $(this);
    if ($('#minim_chat_window').hasClass('panel-collapsed')) {
        $this.parents('.panel').find('.panel-body').slideDown();
        $('#minim_chat_window').removeClass('panel-collapsed');
        $('#minim_chat_window').removeClass('glyphicon-plus').addClass('glyphicon-minus');
    }
});

$(document).on('click', '#new_chat', function (e) {
    var size = $( ".chat-window:last-child" ).css("margin-left");
     size_total = parseInt(size) + 400;
    alert(size_total);
    var clone = $( "#chat_window_1" ).clone().appendTo( ".container" );
    clone.css("margin-left", size_total);
});

$(document).on('click', '.icon_close', function (e) {
    //$(this).parent().parent().parent().parent().remove();
    $( "#chat_window_1" ).remove();
});


$(document).ready(function() {
    $speechInput = $("#btn-input");
    $recBtn = $("#rec");
    $speechInput.keypress(function(event) {
      if (event.which == 13) {
        event.preventDefault();
        send();
        writeMessageIntoThePanel(true, document.getElementById('btn-input').value);
        document.getElementById('btn-input').value = '';
      }
    });
    $recBtn.on("click", function(event) {
      switchRecognition();
    });
    $(".debug__btn").on("click", function() {
      $(this).next().toggleClass("is-active");
      return false;
    });
  });


$(function() {
	$("#btn-chat").click(function() {
		send();
        writeMessageIntoThePanel(true, document.getElementById('btn-input').value);
        document.getElementById('btn-input').value = '';
	});
});

function writeMessageIntoThePanel(isUser, textMessage){
	
	if (isUser){
		
		//Se adiciona el bloque de texto, al panel del chat como enviado por el usuario
//		<div class="row msg_container base_sent">
//			<div class="col-md-10 col-xs-10">
//				<div class="messages msg_sent">
//					<p>that mongodb thing looks good, huh? tiny master db, and
//						huge document store</p>
//					<time datetime="2009-11-13T20:00">Timothy • 51 min</time>
//				</div>
//			</div>
//			<div class="col-md-2 col-xs-2 avatar">
//				<img
//					src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg"
//					class=" img-responsive ">
//			</div>
//		</div>
		
		var div1 = document.createElement("div");
		div1.className = "row msg_container base_sent";
		
		var div1_1 = document.createElement("div");
		div1_1.className = "col-md-10 col-xs-10";
		
		var div1_1_1 = document.createElement("div");
		div1_1_1.className = "messages msg_sent";
		
		var p = document.createElement("p");
		
		var textNode = document.createTextNode(textMessage);
		
		p.appendChild(textNode);
		div1_1_1.appendChild(p);
		div1_1.appendChild(div1_1_1);
		div1.appendChild(div1_1);
		
		var div1_2 = document.createElement("div");
		div1_2.className = "col-md-2 col-xs-2 avatar";
		
		var img = document.createElement("img");
		img.setAttribute("src", "http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg");
		img.setAttribute("class", "img-responsive");
		
		div1_2.appendChild(img);
		div1.appendChild(div1_2);
		
		document.getElementById('allMessagesDiv').appendChild(div1);
	}else{
		
		//document.getElementById('btn-input').value = '';
		
		//Se adiciona el bloque de texto, al panel del chat como enviado por el robot
//		<div class="row msg_container base_receive">
//			<div class="col-md-2 col-xs-2 avatar">
//				<img
//					src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg"
//					class=" img-responsive ">
//			</div>
//			<div class="col-md-10 col-xs-10">
//				<div class="messages msg_receive">
//					<p>that mongodb thing looks good, huh? tiny master db, and
//						huge document store</p>
//					<time datetime="2009-11-13T20:00">Timothy • 51 min</time>
//				</div>
//			</div>
//		</div>
		
		var div1 = document.createElement("div");
		div1.className = "row msg_container base_receive";
		
		var div1_1 = document.createElement("div");
		div1_1.className = "col-md-2 col-xs-2 avatar";
		
		var img = document.createElement("img");
		img.setAttribute("src", "http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg");
		img.setAttribute("class", "img-responsive");
		
		var div1_2 = document.createElement("div");
		div1_2.className = "col-md-10 col-xs-10";
		
		var div1_2_1 = document.createElement("div");
		div1_2_1.className = "messages msg_receive";
		
		var p = document.createElement("p");
		
		var textNode = document.createTextNode(textMessage);
		
		p.appendChild(textNode);
		div1_2_1.appendChild(p);
		div1_2.appendChild(div1_2_1);
		div1_1.appendChild(img);
		div1.appendChild(div1_1);
		div1.appendChild(div1_2);
		
		
		document.getElementById('allMessagesDiv').appendChild(div1);
		
	}
	
	//Se hace scroll hasta el final
	var objDiv = document.getElementById("allMessagesDiv");
	objDiv.scrollTop = objDiv.scrollHeight;
	
};

function startRecognition() {
    recognition = new webkitSpeechRecognition();
    recognition.continuous = false;
        recognition.interimResults = false;
    recognition.onstart = function(event) {
      respond(messageRecording);
      updateRec();
    };
    recognition.onresult = function(event) {
      recognition.onend = null;
      
      var text = "";
        for (var i = event.resultIndex; i < event.results.length; ++i) {
          text += event.results[i][0].transcript;
        }
        setInput(text);
      stopRecognition();
    };
    recognition.onend = function() {
      respond(messageCouldntHear);
      stopRecognition();
    };
    recognition.lang = "es-ES";
    recognition.start();
  }

  function stopRecognition() {
    if (recognition) {
      recognition.stop();
      recognition = null;
    }
    updateRec();
  }
  
  function switchRecognition() {
    if (recognition) {
      stopRecognition();
    } else {
      startRecognition();
    }
  }
  
  function setInput(text) {
    $speechInput.val(text);
    send();
  }
  
  function updateRec() {
    $recBtn.text(recognition ? "Stop" : "Speak");
  }
  
  function send() {
    var text = $speechInput.val();
    //url: baseUrl + "query",
    
    $.ajax({
      type: "POST",
      url: baseUrl,
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      headers: {
        "Authorization": "Bearer " + accessToken
      },
      data: JSON.stringify({query: text ,lang: "en", sessionId: "yaydevdiner"}),
      success: function(data) {
      	var respText = JSON.stringify(data);
      	//var objJson = JSON.parse(respText);
      	
      	//alert(data.result.fulfillment.speech);
      	//console.log("--" + respText + "--");
        
        prepareResponse(data);
      },
      error: function() {
        respond(messageInternalError);
      }
    });
  }
  
  function prepareResponse(val) {
  	//console.log(val);
  	
    	var debugJSON = JSON.stringify(val, undefined, 2),
    		spokenResponse = val.result.fulfillment.speech;
    
    	respond(spokenResponse);
    	debugRespond(debugJSON);
  }
  
  function debugRespond(val) {
    $("#response").text(val);
  }
  

  function respond(val) {
	if (val == "") {
		val = messageSorry;
	}
	if (val !== messageRecording) {

		var msg = new SpeechSynthesisUtterance();
		msg.voiceURI = "native";
		msg.text = val;
		msg.lang = "es-ES";
		window.speechSynthesis.speak(msg);
		
		writeMessageIntoThePanel(false, val);
		document.getElementById('btn-input').value = '';
	}
	//$("#spokenResponse").addClass("is-active").find(".spoken-response__text").html(val);
}