// 書籍情報を表示するための関数
function displayBookInfo(book) {
    const { title, industryIdentifiers, imageLinks } = book.volumeInfo;
    const isbn_10 = industryIdentifiers[0]?.identifier;
    const isbn_13 = industryIdentifiers[1]?.identifier;
    const { thumbnail } = imageLinks;

    const bookDiv = document.createElement("div");
    bookDiv.innerText = `${title} ${isbn_10}`;

    const thumbnailImg = document.createElement("img");
    thumbnailImg.src = thumbnail;
    bookDiv.appendChild(thumbnailImg);

    // Create form
    const bookForm = document.createElement("form");
    bookForm.action = "/reading-books";
    bookForm.method = "post";

    const titleInput = document.createElement("input");
    titleInput.type = "hidden";
    titleInput.name = "title";
    titleInput.value = title;
    bookForm.appendChild(titleInput);

    const isbn10Input = document.createElement("input");
    isbn10Input.type = "hidden";
    isbn10Input.name = "isbn10";
    isbn10Input.value = isbn_10;
    bookForm.appendChild(isbn10Input);

    const isbn13Input = document.createElement("input");
    isbn13Input.type = "hidden";
    isbn13Input.name = "isbn13";
    isbn13Input.value = isbn_13;
    bookForm.appendChild(isbn13Input);

    const thumbnailInput = document.createElement("input");
    thumbnailInput.type = "hidden";
    thumbnailInput.name = "thumbnail";
    thumbnailInput.value = thumbnail;
    bookForm.appendChild(thumbnailInput);

    const submitButton = document.createElement("input");
    submitButton.type = "submit";
    submitButton.value = "登録";
    bookForm.appendChild(submitButton);

    bookDiv.appendChild(bookForm);

    return bookDiv;
}


// 検索結果が空である場合のメッセージを生成する関数
function createNoResultsMessage() {
    const noResultsDiv = document.createElement("div");
    noResultsDiv.innerText = "見つかりませんでした";
    return noResultsDiv;
}

function searchBooks() {
    const query = document.getElementById("searchQuery").value;

    fetch(`/api/books/search?keyword=${query}`)
        .then(response => response.json())
        .then(data => {
            const searchResults = document.getElementById("searchResults");
            searchResults.innerHTML = "";

            if (!data?.items?.length) {
                searchResults.appendChild(createNoResultsMessage());
                return;
            }

            console.log(data.items);
            data.items.forEach(book => {
                const bookInfo = displayBookInfo(book);
                searchResults.appendChild(bookInfo);
            });
        })
        .catch(error => {
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