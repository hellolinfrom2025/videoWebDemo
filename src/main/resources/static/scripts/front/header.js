var urls = {
    login: "/login.do",
    loginOut: '/videoWebFront/index/loginOut.do',
    singUp: '/videoWebFront/index/singUp.do',
    forgPass: '/videoWebFront/index/forgPass.do',
};
var element, form, $, layer;
layui.use(['element', 'form', 'layer'], function () {
    element = layui.element, form = layui.form, $ = layui.jquery, layer = layui.layer;

    //监听登录按钮的弹框
    $('#login').on('click', function () {
        singInWindow();
    });
    //导航栏监听
    element.on('nav(headNav)', function (elem) {
        if (elem[0].innerText === '首页') {
            window.location.href = '/videoWebFront/index/index.html';
        } else if (elem[0].innerText === '分类搜索') {
            location.href = '/videoWebFront/index/category.html?pageNo=1&&' +
                'type=&&country=&&year=&&order=0';
        } else if (elem[0].innerText === '数据统计') {
            if (isLogin()) {
                location.href = '/videoWebFront/dataGraph/index.html';
            } else {
                singInWindow()
            }
        } else if (elem[0].innerText === '个人信息') {
            if (isLogin()) {
                location.href = '/videoWebFront/user/index.html';
            } else {
                singInWindow()
            }
        }
    });
});

//验证码刷新
function refresh() {
    document.getElementById('captcha_img').src = "/captcha/getCaptcha.jpg?" + Math.random();
}

function singInWindow() {
    layer.closeAll();
    layer.open({
        type: 5,
        content: $('#singinhtml').html(),
        area: ['500px', '500px'],
        title: false,
        success: function () {
            singIn();
            $('a[name="singup"]').on('click', function () {
                singUpWindow();
            });
            $('a[name="forgpass"]').on('click', function () {
                forgPassWindow();
            });
        }
    });
}

function singUpWindow() {
    layer.closeAll();
    layer.open({
        type: 5,
        content: $('#singuphtml').html(),
        area: ['500px', '500px'],
        title: false,
        success: function () {
            singUp();
            $('a[name="singin"]').on('click', function () {
                singInWindow();
            });
        }
    });
}

function forgPassWindow() {
    layer.closeAll();
    layer.open({
        type: 5,
        content: $('#forgpasshtml').html(),
        area: ['500px', '400px'],
        title: false,
        success: function () {
            forgPass();
            $('a[name="singin"]').on('click', function () {
                singInWindow();
            });
        }
    });
}

//登录弹框的登录按钮事件
function singIn() {
    form.on("submit(user-login-submit)", function (data) {
        $.ajax({
            url: urls.login,
            data: {
                name: data.field.username,
                password: data.field.password,
                verifyCodeActual: data.field.vercode
            },
            type: 'POST',
            success: function (obj) {
                if (obj.success) {
                    location.reload();
                } else {
                    layer.msg(obj.msg, {icon: 2});
                }
            },
            error: function () {//失败处理函数
                layer.msg('请求异常！', {icon: 2});
            }
        });
        return false;
    })
}

function singUp() {
    form.on("submit(user-reg-submit)", function (data) {
        $.ajax({
            url: urls.singUp,
            data: {
                name: data.field.nickname,
                psw: data.field.password,
                email: data.field.email,
            },
            type: 'POST',
            success: function (obj) {
                if (obj.success) {
                    layer.msg(obj.msg, {icon: 1});
                    setTimeout(function () {
                        singInWindow();
                    }, 2000);
                } else {
                    layer.msg(obj.msg, {icon: 2});
                }
            },
            error: function () {//失败处理函数
                layer.msg('请求异常！', {icon: 2});
            }
        });
        return false;
    })
}

function forgPass() {
    form.on("submit(user-forgpass-submit)", function (data) {
        $.ajax({
            url: urls.forgPass,
            data: {
                psw: data.field.password,
                email: data.field.email,
            },
            type: 'POST',
            success: function (obj) {
                if (obj.success) {
                    layer.msg(obj.msg, {icon: 1});
                    setTimeout(function () {
                        singInWindow();
                    }, 2000);
                } else {
                    layer.msg(obj.msg, {icon: 2});
                }
            },
            error: function () {//失败处理函数
                layer.msg('请求异常！', {icon: 2});
            }
        });
        return false;
    })
}