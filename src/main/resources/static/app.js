$(function(){
$("li").on("click", (function () {
          $("#selected").removeAttr('id');
          $(this).attr('id', "selected");
           var user = $("#selected").text()
          $(document.body).append(getConversionWindow(user));
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

    var input = $("#current").find(".form-control");
    var userPanel = $("#current").find(".panel-heading");
    stompClient.send("/app/hello", {}, JSON.stringify({'content': input.val(),'recipient': userPanel.text()}));
    input.val("");
}

function getConversionWindow(userName){
  return "<div  class=\"btn-group\">\n"+
"        <div class=\"message-container\">\n"+
"            <div class=\"panel panel-default\">\n"+
"                <div class=\"panel-heading\">" + userName + "</div>\n"+
"                <div class=\"panel-body\" id=\"messageArea\" style=\"overflow: scroll; overflow: scroll; width: 300px; height: 250px;\"></div>\n"+
"                <input type=\"text\" class=\"form-control\" placeholder=\"Type your message\"/>\n"+
"                    <span class=\"glyphicon glyphicon-send\"></span>\n"+
"            </div>\n"+
"        </div>\n"+
"    </div>";

}

$(function () {
    $("#removeClass").click(function() { disconnect(); });
    $(document).on('click', '.glyphicon.glyphicon-send', function() { sendName(); });
    $(document).on('click', '.form-control', function(){
        $("#current").removeAttr("id");
        $(this).parent().closest('div').attr('id', 'current')
    })
});





