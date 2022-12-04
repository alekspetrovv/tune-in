import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { Header } from './components/Header'
import { Blogs } from './components/Blogs'
import { DisplayBoard } from './components/DisplayBoard'
import CreateBlog from './components/CreateBlog'
import { getAllBlogs, createBlog, getTest} from './services/BlogService'

class App extends Component {

  state = {
    text: "",
    blog: {},
    blogs: [],
    numberOfBlogs: 0
  }

  CreateBlog = (e) => {
    createBlog(this.state.blog)
      .then(() => {
        this.setState({ numberOfBlogs: this.state.numberOfBlogs + 1 })
      });
    this.setState({ blog: {} })
  }
  GetTest = (e) => {
    getTest(e)
        .then(response => {
          console.log("text is: " + response)
          this.setState({ text: response})
        });
  }

  getAllBlogs = () => {
    getAllBlogs()
      .then(blogs => {
        console.log(blogs)
        this.setState({ blogs: blogs, numberOfBlogs: blogs.length })
      });
  }

  onChangeForm = (e) => {
    let blog = this.state.blog
    if (e.target.name === 'title') {
      blog.title = e.target.value;
    }
    if (e.target.name === 'content') {
      blog.content = e.target.value;
    }
    this.setState({ blog })
  }

  render() {
    return (
      <div className="App">
        <Header></Header>
        <div className="container mrgnbtm">
          <div className="row">
            <div className="col-md-8">
              <CreateBlog
                blog={this.state.blog}
                onChangeForm={this.onChangeForm}
                createBlog={this.CreateBlog}
              >
              </CreateBlog>
            </div>
            <div className="col-md-4">
              <DisplayBoard
                numberOfBlogs={this.state.numberOfBlogs}
                getAllBlogs={this.getAllBlogs}
              >
              </DisplayBoard>
            </div>
          </div>
        </div>
        <div className="row mrgnbtm">
          <Blogs blogs={this.state.blogs}></Blogs>
        </div>
          <div className="btn">
              <button type="button" onClick={(e) => this.GetTest(e)} className="btn btn-warning">Testing</button>
          </div>
      </div>
    );
  }
}

export default App;
