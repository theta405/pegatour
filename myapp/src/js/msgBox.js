var currentMsg = 0, transitionTime = 500, exitBlur = "2px";
var msgRed = "rgb(200,0,0)", msgYellow = "rgb(233,170,4)", msgGreen = "rgb(42, 160, 33)", msgGray = "rgb(100,100,100)";

$(function() {
    $("body").append("<div id = 'defaultBox'><div></div></div>");
});

function popupMsg(message, color, icon, time, xPos, yPos) {
    currentMsg ++;
    var gap = 20, direction, defaultPosition, msgID = "#msg" + currentMsg;

    time = time || 2000;
    defaultPosition = !(xPos || yPos);
    if(yPos > window.innerHeight / 2){
        direction = 1;
    }else{
        direction = -1;
        yPos = yPos + gap * direction || 0;
    }
    fontColor = isLight(color) ? "black" : "white";
    
    var msgBox = $("<div class = 'msgBox' id = 'msg" + currentMsg + "'><div class = 'msgContent'>"+(icon ? "<div class='icon'></div>" : "") + "<p class = 'msgPrompt'>" + message + "</p><div class = 'close'></div></div></div>");
    if(defaultPosition){
        $("#defaultBox>div").prepend(msgBox);
    }else{
        $("body").append(msgBox);
    }

    var outsideClose = "$('" + msgID + "').stop(true,true).animate({'opacity' : '0', 'top' : '" + yPos + "px'}," + transitionTime + ").css('filter' ,'blur(" + exitBlur + ")');$('" + msgID + " .close').css('pointer-events' ,'none');setTimeout('$(\"" + msgID + "\").remove();', 1000);",

   defaultClose = "$('" + msgID + "~.msgBox').stop(true,true).animate({'top': '" + (gap - $(msgID).outerHeight(true)) + "px'}," + transitionTime + ",function(){$('" + msgID + "~.msgBox').css('top' ,'" + gap + "px');});$('" + msgID + "').stop(true,true).animate({'opacity' :'0', 'top' :'" + yPos + "px'}," + transitionTime + ", function(){$('" + msgID + "').css('position' ,'absolute');}).css('filter' ,'blur(" + exitBlur + ")');$('" + msgID + " .close').css('pointer-events' ,'none');setTimeout('$(\"" + msgID + "\").remove();', 1000);";

    $(msgID).css({"background-color": (color || "rgb(100,100,100)"), "opacity" :"0", "top": yPos + "px", "left": (xPos ? xPos + "px" : "50%"), "transform": (xPos ? "" : "translate(-50%,0)"), "position": (defaultPosition ? "relative" : "absolute"), "transition": "filter " + transitionTime / 1000 + "s ease", "filter": "blur(0)", "padding": (8 + $(msgID).outerHeight() * 0.03) + "px " + (8 + $(msgID).outerWidth() * 0.03) + "px"})
    .stop(true,true).animate({
        "opacity": 1,
        "top": yPos - gap * direction + "px"
    }, transitionTime);
        
    $(msgID + " .msgPrompt").css("color", fontColor);
    $(msgID + " .icon").css({"background-image": "url(../images/" + icon + ".svg)", "filter": (isLight(color) ? "brightness(0)" : "brightness(100)")});
    $(msgID + " .close").css({"background-image": "url(../images/quit.svg)", "filter": (isLight(color) ? "brightness(0)" : "brightness(100)")})
    .attr("onclick", (defaultPosition ? defaultClose : outsideClose));

    if(defaultPosition){
        $(msgID + "~.msgBox").stop(true,true)
        .css("top", gap - $(msgID).outerHeight(true))
        .stop(true,true).animate({"top": gap + "px"}, transitionTime);
    }
    if(time != "inf"){setTimeout((defaultPosition ? defaultClose : outsideClose), time);}
}

function isLight(rgb) {
    rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
    return 0.213 * rgb[1] + 0.715 * rgb[2] + 0.072 * rgb[3] > 255 / 2;
}