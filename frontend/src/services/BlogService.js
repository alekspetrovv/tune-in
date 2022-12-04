
export async function getAllBlogs() {
    const response = await fetch('/feed/comments');
    return await response.json();
}

export async function getTest() {
    const response = await fetch('/');
    const final_data = await response.json()
    console.log(final_data)
    return final_data
}

export async function createBlog(data) {
    const response = await fetch(`feed/comment`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    return await response.json();
}

export async function deleteBlog(id) {
    const response = await fetch(`feed/comment/` + id, {
        method: 'DELETE',
    })
    return await response.json();
}   