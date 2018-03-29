var sWord = 'Wait a minute plz, I\'m trying to load the index page.<br/><br/>This is a web site that belongs to Lanyh.<br/><br/>Welcome to here my friend.<br/><br/>Plz input your username & password.<br/><br/>Attention! Input nothing and check the checkbox if you are a visitor.<br/><br/>';
var sChineseWord = '********************************<br/><br/>请稍等一下, 正在努力加载主页。<br><br>这里是Lanyh的个人站点。<br/><br/>欢迎来到这里我的朋友。<br/><br/>请输入您的用户名和密码。<br/><br/>如果是游客请不要输入任何东西并选中检验栏。';
var i = 0;

$(document).ready(function () {
    setTimeout(function () {
        $("#ConfirmCheckDiv").fadeIn(4000);
    }, 1500);

    var newBtn = $("#ConfirmNewBtn");
    var oldBtn = $("#ConfirmNotNewBtn");

    newBtn.click(function () {
        $("#ConfirmCheckDiv").css("display", "none");
        $("body").css("background-image", "linear-gradient(45deg,#000,#000)");
        setTimeout('typing()', 400);
    });

    oldBtn.click(function () {
        $("#ConfirmCheckDiv").css("display", "none");
        setTimeout('check()', 100);
    });

    newBtn[0].onmouseover = oldBtn[0].onmouseover = function () {
        this.style.backgroundColor = '#444444';
    };

    newBtn[0].onmouseout = oldBtn[0].onmouseout = function () {
        this.style.backgroundColor = '#222222';
    };

    $("#FinishReadingBtn").click(function () {
        $("#Typing")[0].innerHTML = "";
        $("#TypingChinese")[0].innerHTML = "";
        $("#FinishReadingBtn").css("display", "none");
        $("body").css("backgroundImage", "linear-gradient(45deg,#000,#222)");
        setTimeout('check()', 100);
    });

});

function typing() {
    if (sWord.charAt(i) === ".") {
        i += 10;
    }
    if (i <= sWord.length) {
        $("#Typing")[0].innerHTML = sWord.slice(0, i++) + '_';
        setTimeout('typing()', 50);//递归调用
    }
    else {
        setTimeout(function () {
            $("#Typing")[0].innerHTML = sWord;//结束打字,移除 _ 光标
            $("#TypingChinese")[0].innerHTML = sChineseWord;
        }, 400);
        setTimeout(function () {
            $("#FinishReadingBtn").fadeIn(3000).click(function () {
                check();
            });
        }, 1500);
    }
}

function check() {
    var oLoginCheckDiv = $("#LoginCheckDiv")[0];
    var oRegisterDiv = $("#RegisterDiv")[0];
    var oUsername = $("#Username")[0];
    var oPassword = $("#Password")[0];
    var oCheckBox = $("#ConfirmVisitor")[0];
    var oRegUsername = $("#RegisterUsername")[0];
    var oRegPassword = $("#RegisterPassword")[0];
    var oRegRePassword = $("#RePassword")[0];
    var oEmail = $("#Email")[0];
    var oAllRegister = $(".Register");
    var bool;

    oLoginCheckDiv.style.display = 'block';

    for (var i = 0; i < oAllRegister.length; i++) {
        oAllRegister[i].onfocus = function () {
            this.style.borderColor = 'black';
        };
        switch (i) {
            case 0:
                oAllRegister[i].onblur = function () {
                    if (this.value.length > 0 && (this.value.length < 6 || this.value.length > 15)) {
                        this.style.borderColor = 'red';
                        bool = false;
                    } else {
                        this.style.borderColor = 'lightgray';
                        bool = true;
                    }
                };
                break;
            case 1:
                oAllRegister[i].onblur = function () {
                    if (this.value.length > 0 && (this.value.length < 6 || this.value.length > 15)) {
                        this.style.borderColor = 'red';
                        bool = false;
                    } else {
                        this.style.borderColor = 'lightgray';
                        bool = true;
                    }

                    if (oRegRePassword.value !== this.value && oRegRePassword.value.length > 0) {
                        oRegRePassword.style.borderColor = 'red';
                        bool = false;
                    } else {
                        oRegRePassword.style.borderColor = 'lightgray';
                        bool = true;
                    }
                };
                break;
            case 2:
                oAllRegister[i].onblur = function () {
                    if (this.value !== oRegPassword.value) {
                        this.style.borderColor = 'red';
                        bool = false;
                    } else {
                        this.style.borderColor = 'lightgray';
                        bool = true;
                    }
                };
                break;
            case 3:
                oAllRegister[i].onblur = function () {
                    if (oEmail.value.length > 0 && (oEmail.value.length < 6 || oEmail.value.indexOf("@") <= 0 || oEmail.value.substring(oEmail.value.length - 4) !== ".com")) {
                        this.style.borderColor = 'red';
                        bool = false;
                    } else {
                        this.style.borderColor = 'lightgray';
                        bool = true;
                    }
                };
                break;
            default:
                oAllRegister[i].onblur = function () {
                    this.style.borderColor = 'lightgray';
                }
        }
    }

    document.getElementById('RegisterBtn').onclick = function () {
        oLoginCheckDiv.style.display = 'none';
        oRegisterDiv.style.display = 'block';
    };

    document.getElementById('ReturnLoginBtn').onclick = function () {
        oRegisterDiv.style.display = 'none';
        check();
    };

    oCheckBox.onclick = function () {
        oUsername.style.borderColor = 'lightgray';
        oPassword.style.borderColor = 'lightgray';
        if (oCheckBox.checked) {
            oUsername.disabled = true;
            oPassword.disabled = true;
        } else {
            oUsername.disabled = false;
            oPassword.disabled = false;
        }
    };

    oUsername.onfocus = function () {
        oUsername.style.borderColor = "black";
    };


    oUsername.onblur = function () {
        oUsername.style.borderColor = "lightgray";

        if (oUsername.value.length > 0 && (oUsername.value.length < 6 || oUsername.value.length > 15)) {
            oUsername.style.borderColor = "red";
        }
    };

    oPassword.onfocus = function () {
        oPassword.style.borderColor = "black";
    };

    oPassword.onblur = function () {
        oPassword.style.borderColor = "lightgray";

        if (oPassword.value.length > 0 && (oPassword.value.length < 6 || oPassword.value.length > 15)) {
            oPassword.style.borderColor = "red";
        }
    };

    document.getElementById('Submit').onclick = function () {
        oUsername.style.borderColor = "lightgray";
        oPassword.style.borderColor = "lightgray";
        var bool = false;

        if (oCheckBox.checked && oUsername.value.length === 0 && oPassword.value.length === 0) {
            return true;
        }

        if (oUsername.value.length < 6 || oUsername.value.length > 15) {
            oUsername.style.borderColor = "red";
            bool = false;
        } else if (oPassword.value.length < 6 || oPassword.value.length > 15) {
            oPassword.style.borderColor = "red";
            bool = false;
        } else {
            bool = true;
        }

        return bool;
    };

    document.getElementById('FinishRegisterBtn').onclick = function () {
        return oRegUsername.value.length > 0 && oRegPassword.value.length > 0 && oEmail.value.length > 0 && oRegPassword.value.length > 0 && bool
    };
}