import React from 'react'


const CreateBlog = ({ onChangeForm, createBlog }) => {

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-7 mrgnbtm">
                    <h2>Create Blog</h2>
                    <form>
                        <div className="row">
                            <div className="form-group col-md-10">
                                <label htmlFor="exampleInputEmail1">Title</label>
                                <input type="text" onChange={(e) => onChangeForm(e)} className="form-control" name="title" id="title" aria-describedby="emailHelp" placeholder="Enter Title" />
                            </div>
                            <div className="form-group col-md-10">
                                <label htmlFor="exampleInputPassword1">Body</label>
                                <input type="text" onChange={(e) => onChangeForm(e)} className="form-control" name="content" id="content" placeholder="Enter Body" />
                            </div>
                        </div>
                        <button type="button" onClick={(e) => createBlog()} className="btn btn-primary">Create</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default CreateBlog