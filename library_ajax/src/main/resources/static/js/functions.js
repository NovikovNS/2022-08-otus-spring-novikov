window.onload = function () {
    getAllBooks()
    getAllAuthors()
    getAllStyles()
}


function getAllBooks() {
    let bookTable = document.getElementById("book-table").getElementsByTagName("tbody")[0]
    bookTable.parentElement.replaceChild(document.createElement('tbody'), bookTable)
    fetch('/api/books')
        .then(res => res.json())
        .then(books => {
            books.forEach(book => {
                let bookRaw = document.getElementById("book-table").getElementsByTagName("tbody")[0].insertRow()
                bookRaw.insertCell(0).innerHTML = book.id;
                bookRaw.insertCell(1).innerHTML = book.name;
                bookRaw.insertCell(2).innerHTML = book.author.name;
                bookRaw.insertCell(3).innerHTML = book.style.name;
                bookRaw.insertCell(4).innerHTML = `<button onclick="prepareEditBook(this)" name="btn-edit"><i class="bi bi-pencil"></i>Edit</button>`;
                bookRaw.insertCell(5).innerHTML = `<button onclick="deleteBookAndResetLibrary(this)" name="btn-trash"><i class="bi bi-trash3"></i>Delete</button>`;
            })
        })
}

function getAllAuthors() {
    let authorsSelector = document.getElementById("book-authors")

    fetch('/api/authors')
        .then(res => res.json())
        .then(authors => {
            authors.forEach(author => {
                authorsSelector.add(new Option(author.name))
            })
        })
}

function getAllStyles() {
    let stylesSelector = document.getElementById("book-styles")

    fetch('/api/styles')
        .then(res => res.json())
        .then(styles => {
            styles.forEach(style => {
                stylesSelector.add(new Option(style.name))
            })
        })
}

function createBookAndResetLibrary() {

}

function updateBookAndResetLibrary() {
    document.getElementById("edit-book").onclick = function () {
        fetch('/api/book/{bookId}')
    }
}

function prepareEditBook(editBtn) {
    let row = editBtn.parentElement.parentElement
    document.getElementById("book-id-input").value = row.cells[0].innerHTML;
    document.getElementById("book-name-input").value = row.cells[1].innerHTML;
    document.getElementById("book-authors").value = row.cells[2].innerHTML;
    document.getElementById("book-styles").value = row.cells[3].innerHTML;
}

function deleteBookAndResetLibrary(delBtn) {
    let bookId = delBtn.parentElement.parentElement.cells[0].innerHTML;
    deleteBookById(bookId).then((response) => getAllBooks())
}

async function deleteBookById(bookId) {
    return await fetch('/api/book/' + bookId, {
            method: 'DELETE'
        }
    )
}

