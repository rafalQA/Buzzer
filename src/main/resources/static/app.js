var conversionWindow = "<div  class=\"btn-group\" id=\"chat-window\">\n"+
                                       "        <div class=\"message-container\">\n"+
                                       "            <div class=\"panel panel-default\">\n"+
                                       "                <div class=\"panel-heading\">Panel Heading</div>\n"+
                                       "                <div class=\"panel-body\" id=\"messageArea\" style=\"overflow: scroll; overflow: scroll; width: 300px; height: 250px;\"></div>\n"+
                                       "                <input type=\"text\" class=\"form-control\" id=\"status_message\" placeholder=\"Type your message\"/>\n"+
                                       "                    <span class=\"glyphicon glyphicon-send\" id=\"send\"></span>\n"+
                                       "            </div>\n"+
                                       "        </div>\n"+
                                       "    </div>";


  $(function(){
$("li").on("click", (function () {
          $(document.body).append(conversionWindow);
           $(this).attr('id', "selected");
            connect();
            }));

            $("#removeClass").click(function () {
          $('#qnimate').removeClass('popup-box-on');
            });
  })

var stompClient = null;

function connect() {
 if(stompClient ==null){
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    }
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue/messenger', function (message) {
            showMessage(JSON.parse(message.body));
        });
    });
}

function showMessage(message) {
    $("#messageArea").append("<h5>" + message.userName+ "</h5><div id=\"message\">"+
            "<p>" + message.content + "</p></div>");
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'content': $("#status_message").val(),'recipient': $("#selected").text()}));
    $("#status_message").val("");
}

$(function () {
    $("#removeClass").click(function() { disconnect(); });
    $(document).on('click', '#send', function() { sendName(); });
});





