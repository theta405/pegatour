var 
    configsGot = false,
    configsUpdated = false;

function showConfigs() {
    $("#configsBlur, #configsBox").css("display", "flex");
    getConfigsList();
}

function closeConfigs() {
    if(configsUpdated) {
        self.location = "/";
    }else {
        $("#configsBlur, #configsBox").css("display", "none");
    }
}

function getConfigsList() {
    if(configsGot) {return;}
    $.ajax({url: "system/getConfigsList", 
        method: "POST", 
        timeout: 10000})
        .then(function(response) {
            for(var s in response) {
                $("#configsLeft").append(`<div class="configName"><a>${response[s]}</a></div>`);
            }
            configsGot = true;
            $(".configName").click(function() {
                $(".configName").attr("id", "");
                $(this).attr("id", "currentConfig");
                getConfigs($(this).children("a").html());
            });
        },
        function() {
            popupMsg("Failed to get configs list<br />Please try again", msgRed, "exclamation");
    });
}

function getConfigs(config) {
    $("#configsRight").html("");
    toggleButtons(false);
    $.ajax({url: "system/getConfigs", 
        method: "POST", 
        data: {
            "config": config.toLowerCase()
        },
        timeout: 10000})
        .then(function(response) {
            for(var s in response) {
                $("#configsRight").append(`
                    <div class="configEntryBox">
                        <p>${s}</p>
                        <input type="text" name="${s}" />
                    </div>
                `);
                $(`input[name="${s}"]`).val(response[s]);
            }
        },
        function() {
            popupMsg("Failed to get configs<br />Please try again", msgRed, "exclamation");
        })
        .always(function() {
            toggleButtons(true);
    });
}

function updateConfigs() {
    module_ = $("#currentConfig").children("a").html();
    data = {"module__": module_.toLowerCase()};
    $.each($("#configsRight").serializeArray(), function(index, item) {
        data[item.name] = item.value;
    });
    toggleButtons(false);
    $.ajax({url: "system/updateConfig", 
        method: "POST", 
        data: data,
        timeout: 10000})
        .then(function(response) {
            if(response == 0) {
                popupMsg(`Updated config of ${module_}`, msgGreen);
                configsUpdated = true;
            }else if(response == 1) {
                popupMsg("Config not updated<br />Because it was not changed", msgGray, "exclamation");
            }else {
                popupMsg("Failed to update configs<br />Please try again", msgRed, "exclamation");
            }
        },
        function() {
            popupMsg("Failed to update configs<br />Please try again", msgRed, "exclamation");
        })
        .always(function() {
            toggleButtons(true);
    });
    return false;
}

function formSubmit() {
    $("#configsRight").submit();
}

function toggleButtons(state) {
    $(".configName, .configsButton, #configsClose").css("pointer-events", state ? "" : "none");
}