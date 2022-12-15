import React from 'react'
import { deleteBlog } from '../services/BlogService'

export const Blogs = ({ blogs: blogs }) => {

    if (blogs.length === 0) return null

    const BlogRow = (blog, index) => {

        return (
            <tr key={index} className={index % 2 === 0 ? 'odd' : 'even'}>
                <td>{index + 1}</td>
                <td>{blog.title}</td>
                <td>{blog.content}</td>
                <td>
                    <button type="button" onClick={(e) => deleteBlog(blog.id)} className="btn btn-danger">Delete</button>
                </td>
            </tr>
        )
    }

    const blogsTable = blogs.map((user, index) => BlogRow(user, index))

    return (
        <div className="container">
            <h2>Blogs</h2>
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>Blog Id</th>
                        <th>Title</th>
                        <th>Body</th>
                    </tr>
                </thead>
                <tbody>
                    {blogsTable}
                </tbody>
            </table>
        </div>
    )
}