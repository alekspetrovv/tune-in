import React from 'react'

export const Blogs = ({blogs: blogs}) => {

    console.log('users length:::', blogs.length)
    if (blogs.length === 0) return null

    const BlogRow = (blog,index) => {

        return(
              <tr key = {index} className={index%2 === 0?'odd':'even'}>
                  <td>{index + 1}</td>
                  <td>{blog.content}</td>
                  {/* <td>{blog.lastName}</td> */}
                  {/* <td>{blog.email}</td> */}
              </tr>
          )
    }

    const blogsTable = blogs.map((user,index) => BlogRow(user,index))

    return(
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