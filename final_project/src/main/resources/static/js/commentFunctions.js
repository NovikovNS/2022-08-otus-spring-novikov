document.getElementById("create-comment-btn").onclick = function () {
    createCommentAndResetComments(this.getAttribute('data-wishId'))
}

function createCommentAndResetComments(wishId) {
    let comment = getCommentInfoForm(wishId)
    createComment(comment)
        .then((response) => getAllComments())
        .then((response) => resetCommentForm())
}

function getCommentInfoForm(wishId) {
    let comment = document.getElementById("comment-input").value
    return {
        comment: comment,
        wishId: wishId
    }
}

function getAllComments() {
    let commentsTable = document.getElementById("wish-table").getElementsByTagName("tbody")[0]
    commentsTable.parentElement.replaceChild(document.createElement('tbody'), commentsTable)
    fetch('/api/comments')
        .then(res => res.json())
        .then(comments => {
            comments.forEach(comment => {
                let commentRaw = document.getElementById("wish-table").getElementsByTagName("tbody")[0].insertRow()
                commentRaw.insertCell(0).innerHTML = comment.comment;
            })
        })
}

function resetCommentForm() {
    document.getElementById("comment-input").value = ''
}

async function createComment(comment) {
    return await fetch('http://localhost:8080/api/comments', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(comment)
    })
}
