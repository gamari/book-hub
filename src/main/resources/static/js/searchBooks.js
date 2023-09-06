// グローバル
let currentPage = 1;
let totalPages = 0;

function checkForEnter(event) {
    if (event.keyCode === 13) {
        searchBooks();
    }
}

// TODO リファクタリングする
// 書籍情報を表示するための関数
function createBookInfoDiv(book) {
    const { title, industryIdentifiers, imageLinks, publishedDate, description } = book.volumeInfo;

    if (!industryIdentifiers?.length || !imageLinks || !publishedDate) {
        return null;
    }

    const isbn_10 = industryIdentifiers[0]?.identifier;
    const isbn_13 = industryIdentifiers[1]?.identifier;
    const { thumbnail } = imageLinks;

    const bookDiv = document.createElement("div");
    bookDiv.className = "result";

    const leftDiv = document.createElement("div");
    const rightDiv = document.createElement("div");
    bookDiv.appendChild(leftDiv);
    bookDiv.appendChild(rightDiv);

    const thumbnailLink = document.createElement("a");

    if (isbn_10) {
        thumbnailLink.href = `/books/isbn/${isbn_10}`;
    } else {
        thumbnailLink.href = `/books/isbn/${isbn_13}`;
    }

    leftDiv.appendChild(thumbnailLink);
    const thumbnailImg = document.createElement("img");
    thumbnailImg.src = thumbnail;
    thumbnailLink.appendChild(thumbnailImg);

    const bookTitle = document.createElement("h3");
    bookTitle.innerText = title;
    rightDiv.appendChild(bookTitle);

    // TODO フォーマットを変更したい
    const bookPublishedDate = document.createElement("p");
    bookPublishedDate.innerText = publishedDate;
    rightDiv.appendChild(bookPublishedDate);

    const bookDescription = document.createElement("p");
    bookDescription.innerText = description;
    rightDiv.appendChild(bookDescription);

    const bookForm = document.createElement("form");
    bookForm.action = "/reading-books";
    bookForm.method = "post";
    rightDiv.appendChild(bookForm);

    // input:hidden 要素を追加する
    const titleInput = document.createElement("input");
    titleInput.type = "hidden";
    titleInput.name = "title";
    titleInput.value = title;
    bookForm.appendChild(titleInput);

    const descriptionInput = document.createElement("input");
    descriptionInput.type = "hidden";
    descriptionInput.name = "description";
    descriptionInput.value = description;
    bookForm.appendChild(descriptionInput);

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

    if (!query) {
        alert("検索内容を入力してください")
        return;
    }

    const searchResults = document.getElementById("results");
    searchResults.innerHTML = "";

    fetchData(query, currentPage);

}

function updatePageInfo() {
    document.getElementById("pageInfo").innerText = `Page ${currentPage} of ${totalPages}`;

    document.getElementById("prevButton").disabled = currentPage <= 1;
    document.getElementById("nextButton").disabled = currentPage >= totalPages;
}

function fetchData(query, page) {
    const searchResults = document.getElementById("results");
    fetch(`/api/books/search?keyword=${query}&page=${page}`)
        .then(response => response.json())
        .then(data => {
            console.log(data);

            if (data.totalItems) {
                totalPages = Math.ceil(data.totalItems / 5);
            }

            if (!data?.items?.length) {
                searchResults.appendChild(createNoResultsMessage());
                return;
            }

            data.items.forEach(book => {
                const bookInfo = createBookInfoDiv(book);
                if (bookInfo) {
                    searchResults.appendChild(bookInfo);
                }
            });

            updatePageInfo();
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

// 初期化
document.getElementById("nextButton").addEventListener("click", function () {
    // TODO 制限を入れる
    currentPage++;
    searchBooks();
});

document.getElementById("prevButton").addEventListener("click", function () {
    if (currentPage > 1) {
        currentPage--;
        searchBooks();
    }
});