function confirmMsg() {
    return confirm('ナレッジを削除しますか？');
}
function ajaxError() {
    alert('サーバとの通信でエラーが発生しました。');
}
jsf.ajax.addOnError(ajaxError);
