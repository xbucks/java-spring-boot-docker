/**
 * Author: DVQuan
 */
var page = require('webpage').create();
var fs = require('fs');
var system = require('system');

var url = "";
var searchParameter = "";

if (system.args.length === 3) {
    url=system.args[1];
    searchParameter=system.args[2];
}

if(url==="" || searchParameter===""){
    phantom.exit();
}

page.settings.userAgent = 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36';

page.zoomFactor = 0.1;

page.viewportSize = {
  width: 1920,
  height: 1080
};

var divCount="-1";
var topPosition=0;
var unchangedCounter=0;

page.open(url, function(status) {
    console.log("Status: " + status);
    if(status === "success") {

        window.setInterval(function() {

            var newDivCount = page.evaluate(function() { 
                var divs = document.querySelectorAll(".rg_di.rg_bx.rg_el.ivg-i");
                return divs[divs.length-1].getAttribute("data-ri");
            });

            topPosition = topPosition + 1080;

            page.scrollPosition = {
                top: topPosition,
                left: 0
            };

            if(newDivCount===divCount){
                page.evaluate(function() {
                    var button = document.querySelector("#smb");
                    if(!(typeof button === "undefined")) {
                        button.click();
                        return true;
                    }else{
                        return false;
                    }
                });

                if(unchangedCounter===5){
                    var path = searchParameter+'.html';
                    fs.write(path, page.content, 'w');
                    phantom.exit();
                }else{
                    unchangedCounter=unchangedCounter+1;
                }
            }else{
                unchangedCounter=0;
            }
            divCount = newDivCount;

        }, 500);
    }else{
        phantom.exit();
    }
});