const 
    TITLE_LENGTH = 35,
    DESC_LENGTH = 250,
    MAX_PAGE_BUTTONS = 7; // Should not be less than 7

var 
    pageSize = 0,
    keyword = "", kwTemp = "";


$(function() {
    $("#searchBox").keyup(function(event) {
        if(event.which == 13) {
            search();
        }
    });
    $("#searchBox").focus();
    getSearchSystems();
});


function search(page) {
    keywords = page ? keyword : $("#searchBox").val();
    if(!keywords) {popupMsg("Please type in some keywords", msgGray, "exclamation"); return;}
    kwTemp = keywords;
    page = page || 1;
    start = (page - 1) * pageSize;
    gotoTop();
    $.ajax({url: `search/${$("#systems").val()}`, 
        method: "POST", 
        data: {
            "keywords": keywords,
            "start": start
        }, 
        timeout: 10000})
        .then(function(response) {
            displayResults(response, start);
        },
        function() {
            popupMsg("Connection to Elastic Search server failed<br />Please check your configs", msgRed, "exclamation");
    });
}


function gotoTop(){
    $("html").stop(true, true).animate({"scrollTop": 0}, 200, "swing");
}


function getSearchSystems() {
    $.ajax({url: "system/getSearch", 
        method: "POST", 
        timeout: 10000})
        .then(function(response) {
            for(var s in response) {
                $("#systems").append(`<option>${response[s]}</option>`);
            }
        },
        function() {
            popupMsg("Failed to get available search systems", msgRed, "exclamation");
    });
}


function cutLongString(str, length) {
    if(str.length >= length) {
        return str.substring(0, length - 3) + "...";
    }else {
        return str;
    }
}


function displayResults(response, start) {
    total = response.total.value;
    if(total) {
        keyword = kwTemp;
        pageSize = response.page_size;
        $("#searchBox").val(keyword);
        $("#resultsBox").html("");
        for(var r in response.hits) {
            hit = response.hits[r]._source;
            tags = hit.Tags.split(", ");
            for(var t in tags) {
                tags[t] = `<a class="tags tags-light-green">${tags[t]}</a>`;
            }
            tags = tags.join("");
            $("#resultsBox").append(`
                <div class="result" onclick="window.open('${hit.Url}')">
                    <div class="resultBox">
                        <div class="titleBox">
                            <a class="resultTitle">${cutLongString(hit.Title, TITLE_LENGTH)}</a>
                            <a class="tags tags-green">${hit["Game Version"]}</a>
                        </div>
                        <a class="publishedTime">${hit["Published time"]}</a>
                    </div>
                    <p class="resultDescription">
                        ${cutLongString(hit.Description, DESC_LENGTH)}
                    </p>
                    <div class="resultBox">
                        <div class="tagsBox noScroll">
                            ${tags}
                        </div>
                        <div class="resultData">
                            <img src="static/images/views.svg" alt="views" class="views" />
                            <a>${hit.Views}</a>
                            <img src="static/images/downloads.svg" alt="downloads" class="downloads" />
                            <a>${hit.Downloads}</a>
                        </div>
                    </div>
                </div>
            `)
        }
        displayPages(total, start);
    }else {
        popupMsg("OOPS!<br />We can not find any related entries<br />Please try another keyword", msgRed, "exclamation");
    }
}


function displayPages(total, start) {
    pageEnd = start + (+pageSize);
    $("#searchIndicator").html(`About ${total} results, displaying ${start + 1} - ${pageEnd <= total ? pageEnd : total}`);

    function range(lower, upper) {
        lower = lower || 0;
        return [...Array(upper + 1).keys()].slice(lower);
    }

    function generatePartialList(small, big) {
        half = Math.floor(MAX_PAGE_BUTTONS / 2);
        if(big - small <= half - 1) {
            return range(small, big);
        }else {
            if(small == 1) {
                return [1, 0].concat(range(big - half + 3, big));
            }else {
                return range(small, small + half - 3).concat([0, big]);
            }
        }
    }

    totalPages = Math.ceil(total / pageSize);
    current = start / pageSize + 1;

    if(totalPages == 1) {return;}
    else if(totalPages <= MAX_PAGE_BUTTONS) {
        pages = range(1, totalPages);
    }else {
        pages = generatePartialList(1, current - 1).concat([current]).concat(generatePartialList(current + 1, totalPages));
    }

    $("#resultsBox").append(`<div id="pagesBox"></div>`);
    for(var p in pages) {
        page = pages[p];
        if(page == 0) {
            $("#pagesBox").append(`<div class="page pageEllipsis"><a>···</a></div>`);
        }else {
            $("#pagesBox").append(`<div class="page"${page == current ? " id='currentPage'" : ""}><a>${page}</a></div>`);
        }
    }

    $(".page").click(function() {
        search($(this).children("a").html());
    });
}