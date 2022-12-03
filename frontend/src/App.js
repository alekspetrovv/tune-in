import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { Header } from './components/Header'
import { Blogs } from './components/Users'
import { DisplayBoard } from './components/DisplayBoard'
import CreateUser from './components/CreateUser'
import { getAllUsers, createUser } from './services/UserService'

class App extends Component {

  state = {
    // user: {},
    blogs: [],
    numberOfBlogs: 0
  }

  // createUser = (e) => {
  //     createUser(this.state.user)
  //       .then(response => {
  //         console.log(response);
  //         this.setState({numberOfUsers: this.state.numberOfUsers + 1})
  //     });
  //     this.setState({user: {}})
  // }

  getAllBlogs = () => {
    getAllUsers()
      .then(blogs => {
        console.log(blogs)
        this.setState({blogs: blogs, numberOfBlogs: blogs.length})
      });
  }

  // onChangeForm = (e) => {
  //     let user = this.state.user
  //     if (e.target.name === 'firstname') {
  //         user.firstName = e.target.value;
  //     } else if (e.target.name === 'lastname') {
  //         user.lastName = e.target.value;
  //     } else if (e.target.name === 'email') {
  //         user.email = e.target.value;
  //     }
  //     this.setState({user})
  // }

  render() {
    
    return (
      <div className="App">
        <Header></Header>
        <div className="container mrgnbtm">
          <div className="row">
            <div className="col-md-8">
                {/* <CreateUser 
                  user={this.state.user}
                  onChangeForm={this.onChangeForm}
                  createUser={this.createUser}
                  >
                </CreateUser> */}
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
      </div>
    );
  }
}

export default App;
