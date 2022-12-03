import React from 'react'

export const DisplayBoard = ({numberOfBlogs, getAllBlogs}) => {
    
    return(
        <div className="display-board">
            <h4>Blogs Created</h4>
            <div className="number">
            {numberOfBlogs}
            </div>
            <div className="btn">
                <button type="button" onClick={(e) => getAllBlogs()} className="btn btn-warning">Get all Blogs</button>
            </div>
        </div>
    )
}