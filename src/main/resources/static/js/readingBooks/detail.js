document.addEventListener("DOMContentLoaded", () => {
    const BOOK_ID = document.body.getAttribute('data-book-id');
    const memoTab = document.getElementById("memo-tab");
    const reviewTab = document.getElementById("review-tab");
    const memoForm = document.getElementById("memo-form");
    const reviewForm = document.getElementById("review-form");

    memoTab.addEventListener("click", () => {
        memoTab.classList.add("active");
        reviewTab.classList.remove("active");
        memoForm.classList.remove("hidden");
        reviewForm.classList.add("hidden");
    });

    reviewTab.addEventListener("click", () => {
        reviewTab.classList.add("active");
        memoTab.classList.remove("active");
        reviewForm.classList.remove("hidden");
        memoForm.classList.add("hidden");
    });

    // フォームの機能実装
    memoForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const formData = new FormData(memoForm);
        const content = formData.get("content");
        console.log(memoForm)

        fetch(`/api/reading-books/${BOOK_ID}/memos`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ content: content }),
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                const memoListContainer = document.querySelector('.memo-list-container');
                const newMemo = document.createElement('div');
                newMemo.className = 'memo-item';
                newMemo.innerHTML = `<span>${data.content}</span>`;
                memoListContainer.prepend(newMemo);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    });
});