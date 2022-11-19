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
        $('#btn-comment-delete').on('click', function (){
            _this.commentDelete();
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

        if (!data.title || data.title.trim() === "" || !data.content || data.content ===""){
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        }
        else{
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
        }
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
        var user_name = $('#user_name').val();
        var room_name = $('#title').text();
        var user_Id = $('#user').val();

        const result = confirm("입장하시겠습니까?");
        if(result==true) {
            localStorage.setItem('wschat.sender', user_name);
            localStorage.setItem('wschat.roomId', roomId);
            localStorage.setItem('wschat.roomName', room_name);
            localStorage.setItem('wschat.userId', user_Id);     //현재 접속유저 id
            window.open("/chat/room/enter/" + roomId);
        }

    },
    commentSave: function () {
        var data = {
            comment: $('#comment').val()
        }
        var postsId =  $('#postsId').val()
        if (!data.comment || data.comment.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        } else {
            $.ajax({
                type: 'POST',
                url: '/api/posts/comments/' + postsId,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('댓글이 등록되었습니다.');
                window.location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    },
    commentDelete: function(){
        var comment_Id = $('#commentsId').val();
        var comment_UserId = $('#comments_userId').val();
        var userId = $('#user').val();

        const result = confirm("정말 댓글을 삭제하시겟습니까?");
        if (result==true){
            if (comment_UserId == userId){
                $.ajax({
                    type: 'DELETE',
                    url: '/api/posts/comments/' + comment_Id,
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8'
                }).done(function () {
                    alert('댓글이 삭제되었습니다.');
                    window.location.reload();
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
            }
            else{
                alert("댓글 작성자가 아닙니다!!");
                return false;
            }
        }
        else{
            return false;
        }
    }
};

main.init();