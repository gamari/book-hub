document.addEventListener("DOMContentLoaded", function () {
    const registerButton = document.getElementById("register-button");
    const unregisterButton = document.getElementById("unregister-button");

    if (registerButton) {
        registerButton.addEventListener("click", function () {
            console.log("登録処理");
            // TODO: ここでAjaxを使ってサーバーに登録処理のリクエストを送る
            // サーバー処理が成功したら、UIを更新する（例えば、ボタンのテキストを変更する）
        });
    }

    if (unregisterButton) {
        unregisterButton.addEventListener("click", function () {
            const book_id = unregisterButton.getAttribute("data-book-id");
            console.log("解除処理", book_id);
            fetch("/api/reading-books/unregister", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    bookId: book_id,
                }),
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        registerButton.style.display = "none";
                        unregisterButton.style.display = "block";
                    }
                })
                .catch(error => console.error("Error:", error));
        });
    }
});