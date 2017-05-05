
var user;
$(function(){
$("li").on("click", (function () {
          $("#selected").removeAttr('id');
          $(this).attr('id', "selected");
           user = $("#selected").text()
          $(document.body).append(getConversionWindow());
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
    var area = $(".panel-heading");

    area.append("<h5>" + message.senderName + "</h5><div id=\"message\">"+
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
    var area = $("#current").find(".panel-body");
    area.append("<h5>" + $("h1").text() + "</h5><div id=\"message\">"+
                "<p>" + input.val() + "</p></div>");
    input.val("");
}

function getConversionWindow(){
  return "<div  class=\"btn-group\">\n"+
"        <div class=\"message-container\">\n"+
"            <div class=\"panel panel-default\">\n"+
"                <div class=\"panel-heading\">" + user + "</div>\n"+
"                <div class=\"panel-body\" style=\"overflow: scroll; overflow: scroll; width: 300px; height: 250px;\"></div>\n"+
"                <input type=\"text\" class=\"form-control\" placeholder=\"Type your message\"/>\n"+
"                    <span class=\"glyphicon glyphicon-send\"></span>\n"+
"            </div>\n"+
"        </div>\n"+
"    </div>";

}

$(function () {
    $("#removeClass").click(function() { disconnect(); });
    $(document).on('click', '.glyphicon.glyphicon-send', function() { sendName(); });
    $(document).on('click', '.btn-group', function(){
        $("#current").removeAttr("id");
        $(this).parent().closest('div').attr('id', 'current')
    })
});





