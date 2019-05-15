//验证码刷新
function refresh() {
    document.getElementById('captcha_img').src = "/captcha/getCaptcha.jpg?" + Math.random();
}

layui.use(['element', 'form', 'layer'], function () {
    var element = layui.element, form = layui.form, $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : parent.layer;
    var urls = {
        login: "/login.do",
        loginOut: '/videoWebFront/index/loginOut.do',
        singUp: '/videoWebFront/index/singUp.do',
        forgPass: '/videoWebFront/index/forgPass.do',
    };
    var fun = {
        singinOpen: function () {
            layer.closeAll();
            layer.open({
                type: 5,
                content: $('#singinhtml').html(),
                area: ['500px', '500px'],
                title: false,
                success: function () {
                    fun.singin();
                    $('a[name="singup"]').on('click', function () {
                        fun.singupOpen()
                    });
                    $('a[name="forgpass"]').on('click', function () {
                        fun.forgpassOpen()
                    });
                }
            });
        },
        singupOpen: function () {
            layer.closeAll();
            layer.open({
                type: 5,
                content: $('#singuphtml').html(),
                area: ['500px', '500px'],
                title: false,
                success: function () {
                    fun.singup();
                    $('a[name="singin"]').on('click', function () {
                        fun.singinOpen()
                    });
                }
            });
        },
        forgpassOpen: function () {
            layer.closeAll();
            layer.open({
                type: 5,
                content: $('#forgpasshtml').html(),
                area: ['500px', '400px'],
                title: false,
                success: function () {
                    fun.forgpass();
                    $('a[name="singin"]').on('click', function () {
                        fun.singinOpen()
                    });
                }
            });
        },
        //登录弹框的登录按钮事件
        singin: function () {
            form.on("submit(user-login-submit)", function (data) {
                $.ajax({
                    url: urls.login,
                    data: {
                        name: data.field.username,
                        password: data.field.password,
                        verifyCodeActual: data.field.vercode,
                        loginType: 'front'
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
        },
        singup: function () {
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
                                fun.singinOpen();
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
        },
        forgpass: function () {
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
                                fun.singinOpen();
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
        },
    };
    //监听登录按钮的弹框
    $('#login').on('click', function () {
        fun.singinOpen();
    });
    //导航栏监听
    element.on('nav(headNav)', function (elem) {
        if (elem[0].innerText === '首页') {
            window.location.href = '/videoWebFront/index/index.html';
        } else if (elem[0].innerText === '分类搜索') {
            // $('iframe').attr('src', '/403.html');
        } else if (elem[0].innerText === '数据统计') {
            if (isLogin()) {
                // $('iframe').attr('src', '/404.html');
            } else {
                fun.singinOpen();
            }
        } else if (elem[0].innerText === '个人信息') {
            if (isLogin()) {
                // $('iframe').attr('src', '/404.html');
            } else {
                fun.singinOpen();
            }
        }
    });
});