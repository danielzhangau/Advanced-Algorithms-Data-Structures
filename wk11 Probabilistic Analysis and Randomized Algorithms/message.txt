import React, { Component } from 'react';

export class dbconnect extends Component {
  constructor(props){
    super(props)
    this.state = {
        apiData: [],
        naData: []
    }
    this.uid = null;
    this.name = null;
    this.email = null;
    this.password = null;
  }
  getname(){
    return this.name;
  }
  onGetUsers () {
    console.log("here is get users");
   fetch('http://localhost:19006/users',{
     method: 'GET'
   }).then((response) => {
     return response.json();
   }).then((jsonData) => {
        this.setState({
         apiData: jsonData,
        })
        console.log(this.state.apiData);
      })
      .catch((error) => {
        console.warn(error);
      });
      this.uid = null;
  }

    onPostUser = () => {
     console.log("here is post users");
     fetch('http://localhost:19006/users',{
       method: 'POST',
       headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
       },
       body: JSON.stringify({ name: this.name, email: this.email, password: this.password })
     }).then((response) => {
       console.log("i am here!!");
       return response.json();
     }).then((jsonData) => {
          this.setState({
           naData: jsonData,
          })
          console.log(this.state.naData);
        })
        .catch((error) => {
          console.warn(error.message);
        });
        this.uid = null;
        this.name = null;
        this.email = null;
        this.password = null;
    }

  onGetUser = () => {
   fetch('http://localhost:19006/users/'+ this.uid,{
     method: 'GET'
   }).then((response) => {
     return response.json();
   }).then((jsonData) => {
        this.setState({
         apiData: jsonData,
        })
        console.log(this.state.apiData);
      })
      .catch((error) => {
        console.log(error.message);
      }).done();
      this.uid = null;
  }

    onDeleteUser = () => {
     fetch('http://localhost:19006/users/'+ this.uid,{
       method: 'DELETE'
     }).then((response) => {
       console.log(response.rows);
     }).catch((error) => {
          console.warn(error);
        }).done();
        this.uid = null;
    }

  onPutUser = () => {
     fetch('http://localhost:19006/users',{
       method: 'PUT',
       headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
       },
       body: JSON.stringify({ name: this.name, email: this.email, password: this.password, id: this.uid })
     }).then((response) => {
       return response.json();
     }).catch((error) => {
          console.warn(error);
        }).done();
        this.uid = null;
        this.name = null;
        this.email = null;
        this.password = null;
  }
};
export function test2(i) {
    console.log(i + 5);
}
