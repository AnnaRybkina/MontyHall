import * as React from 'react';
import './App.css';

export default class App extends React.Component<{}, any> {

  constructor(props: any) {
    super(props);
    this.state = {
      doors: [],
      isLoading: false,
      iterations: 0,
      result: 0,
      alwaysSwitch: true,
      error: false,
      errorMessage: '',
    };
    this.createSimulator.bind(this);

  }

  updateInputValue = (evt: any) => {
    const iterations = (evt.target.validity.valid) ? evt.target.value : this.state.iterations;
    this.setState({
      iterations,
    });
  }

  handleOptionChange = (changeEvent: any) => {
    this.setState({
      alwaysSwitch: changeEvent.target.value === 'true'
    });
  }
  
  createSimulator() {
    this.setState({error: false});
    fetch('http://localhost:8080/data', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        'iterations': this.state.iterations,
        'alwaysSwitch': this.state.alwaysSwitch,
      })
    })
    .then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        this.setState({
          error: true,
          errorMessage: 'Server response not OK',
        });  
        return;
      }
    })
    .then((wins) => {
      this.setState({
        wins,
      });
    })
    .catch( (err) => {
      this.setState({
        error: true,
        errorMessage: 'Server error',
      });
    });
  }

  handleSubmit(e: any) {
    e.preventDefault();
    this.createSimulator.call(this);
  }

  render() {
    const result = this.state.error ? 
      <div className="error_container flex_item"><h2>{this.state.errorMessage}</h2></div> : 
      <div className="result_container flex_item"><h2>{this.state.wins}</h2></div>;
  
    return (
      <div className="app">
        <div className="app_header">
          <h2>ComHem Monty Hall Simulator</h2>
        </div>
        <div className="wrapper">
          <div className="data_container flex_item">
            <div className="iterations">
              <label htmlFor="iterations_input">How many iterations do you want to run?</label>
              <input 
                type="text" 
                id="iterations_input" 
                pattern="[0-9]*" 
                value={this.state.iterations} 
                onChange={this.updateInputValue}
              />
            </div>
            <div className="switch_field">
              <div className="switch_title">Do you want to always switch door?</div>
              <div className="switch_radio">
                <input 
                  type="radio" 
                  id="switch_left"
                  name="switch_2" 
                  value="true" 
                  checked={this.state.alwaysSwitch}
                  onChange={this.handleOptionChange} 
                />
                <label htmlFor="switch_left">Yes</label>
                <input 
                  type="radio" 
                  id="switch_right" 
                  name="switch_2" 
                  value="false" 
                  onChange={this.handleOptionChange} 
                />
                <label htmlFor="switch_right">No</label>
              </div>
            </div>
          </div>
          <div className="button_container flex_item">
            <button onClick={() => this.createSimulator()} className="result_button">Check wins</button>
          </div>
          {result}
        </div>
      </div>
    );
  }}