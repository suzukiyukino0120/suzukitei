$(function(){
    $("#cancel-reservation").on("click",function(){
        if(window.confirm('この予約をキャンセルしますか？')) {
            return true;
        } else {
            return false;
        }
    });
});