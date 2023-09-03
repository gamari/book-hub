function searchBooks() {
    const query = document.getElementById("searchQuery").value;

    fetch(`/api/books/search?keyword=${query}`)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);


            const searchResults = document.getElementById("searchResults");
            searchResults.innerHTML = "";

            if (!data?.items?.length) {
                // 検索結果が空の場合
                const noResultsDiv = document.createElement("div");
                noResultsDiv.innerText = "見つかりませんでした";
                searchResults.appendChild(noResultsDiv);
            } else {
                // 検索結果がある場合
                data.items.forEach((book) => {
                    const { title, industryIdentifiers } = book.volumeInfo;
                    const isbn = industryIdentifiers[1].identifier;

                    const bookDiv = document.createElement("div");
                    bookDiv.innerText = title + " " + isbn;

                    const addButton = document.createElement("button");
                    addButton.innerText = "登録";
                    addButton.onclick = () => addReadingBook(isbn);

                    bookDiv.appendChild(addButton);
                    searchResults.appendChild(bookDiv);
                });
            }
        })
        .catch((error) => {
            console.error("Error:", error);
        });
}

function addReadingBook(bookId) {
    // ReadingBookテーブルに追加（サーバーサイドと通信）
}

function openModal() {
    // モーダルを表示
}

function addReadingBookByTitle() {
    // タイトルによってReadingBookテーブルに追加（サーバーサイドと通信）
}