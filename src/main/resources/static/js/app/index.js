var main = {
    init: function () {
        var _this = this;
        $('#btn-login-google').on('click', function () {
            _this.login_google();
        });
        $('#btn-login-naver').on('click', function () {
            _this.login_naver();
        });
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
        $('#btn-enterRoom').on('click', function () {
            _this.enterRoom();
        });
        $('#btn-comment-save').on('click', function () {
            _this.commentSave();
        });
    },
    login_google: function () {
        window.location.href = '/oauth2/authorization/google';
    },
    login_naver: function () {
        window.location.href = '/oauth2/authorization/naver';
    },
    save: function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            email: $('#email').val(),
            content: $('#content').val()
        };

        var currentId = $('#currentId').val();

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts/' + currentId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    enterRoom: function () {
        var roomId = $('#btn-enterRoom').val();
        var sender = $('#user').text();
        var room_name = $('#title').val();

        localStorage.setItem('wschat.sender', sender);
        localStorage.setItem('wschat.roomId', roomId);
        localStorage.setItem('wschat.roomName', room_name);
        location.href = "/chat/room/enter/" + roomId;
    },
    commentSave: function () {
        var data = {
            postsId: $('#postsId').val(),
            comment: $('#comment').val()
        }
        if (!data.comment || data.comment.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        } else {
            $.ajax({
                type: 'POST',
                url: '/api/posts/comments/' + data.postsId,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('댓글이 등록되었습니다.');
                window.location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }
};

main.init();