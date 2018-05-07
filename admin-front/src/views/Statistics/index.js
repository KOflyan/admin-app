import React from 'react';
import { Bar, HorizontalBar, Pie } from 'react-chartjs-2';
import ApiConnection from './../../utils/ApiConnection';

class Statistics extends React.Component {
  render() {
    return (
      <div className="card" style={{'paddingTop': '10px'}}>
        <div className="row">
          <div className="col-6">
            <UsersByLanguageGraph />
          </div>
          <div className="col-6">
            <DeviceTypesGraph />
          </div>
        </div>
        <div className="row">
          <div className="col-6">
            <AccountTypesGraph />
          </div>
          <div className="col">
            <div className="card border-info mb-3">
              <div className="card-header">
                List all corporate users or show user statistics
              </div>
              <div className="card-body">
                <div className="card border-primary mb-3">
                  <a href="/stats" className="btn btn-fix text-left">
                    <div className="card-block">
                      <h4 className="card-title text-dark ">Statistics</h4>
                      <p className="card-text "><small className="text-muted">Show users statistics</small></p>
                    </div>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

class UsersByLanguageGraph extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      labels: [],
      data: []
    }
  }

  getDataOnLoad = () => {
    ApiConnection.countByLanguage(apiData => {
      const labels = [];
      const data = [];
      apiData.map((elem) => {
        labels.push(elem.language.toUpperCase());
        data.push(elem.count);
        return null;
      })
      this.setState({labels: labels, data: data});
    });
  }

  componentDidMount() {
    this.getDataOnLoad();
  }

  render() {
    const data = {
    labels: this.state.labels,
    datasets: [{
        label: 'Users by language',
        backgroundColor: 'rgba(255,128,0,0.3)',
        borderColor: 'rgba(255,128,0,1)',
        borderWidth: 1,
        hoverBackgroundColor: 'rgba(255,128,0,0.5)',
        hoverBorderColor: 'rgba(255,128,0,1)',
        data: this.state.data
      }]
  };
    return (
      <div className="card border-info mb-3">
        <div className="card-header">
          Users grouped by language
        </div>
        <div className="card-body">
          <Bar data={data}
            options={{
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }}
          />
        </div>
      </div>
    )
  }
}

class DeviceTypesGraph extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      labels: [],
      data: []
    }
  }

  getDataOnLoad = () => {
    ApiConnection.countByDeviceType(apiData => {
      const labels = [];
      const data = [];
      apiData.map((elem) => {
        labels.push(elem.family);
        data.push(elem.count);
        return null;
      })
      this.setState({labels: labels, data: data});
    });
  }

  componentDidMount() {
    this.getDataOnLoad();
  }

  render() {
    const data = {
    labels: this.state.labels,
    datasets: [{
        data: this.state.data,
        backgroundColor: [
      		'#FF6384',
      		'#36A2EB',
      		'#FFCE56'
      		],
      		hoverBackgroundColor: [
      		'#FF335F',
      		'#1586D1',
      		'#FFBE1A'
      ]
    }]
  };
    return (
      <div className="card border-info mb-3">
        <div className="card-header">
          Device grouped by device type
        </div>
        <div className="card-body">
          <Pie
          data={data}
          />
        </div>
      </div>
    )
  }
}

class AccountTypesGraph extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      labels: [],
      data: []
    }
  }

  getDataOnLoad = () => {
    ApiConnection.countByAccountType(apiData => {
      const labels = [];
      const data = [];
      apiData.map((elem) => {
        labels.push(elem.accountType);
        data.push(elem.count);
        return null;
      })
      this.setState({labels: labels, data: data});
    });
  }

  componentDidMount() {
    this.getDataOnLoad();
  }

  render() {
    const data = {
    labels: this.state.labels,
    datasets: [{
      label: 'Accounts by types',
      backgroundColor: 'rgba(255,99,132,0.2)',
      borderColor: 'rgba(255,99,132,1)',
      borderWidth: 1,
      hoverBackgroundColor: 'rgba(255,99,132,0.4)',
      hoverBorderColor: 'rgba(255,99,132,1)',
      data: this.state.data
    }]
  };
    return (
      <div className="card border-info mb-3">
        <div className="card-header">
          Accounts grouped by account type
        </div>
        <div className="card-body">
          <HorizontalBar data={data}
            options={{
                scales: {
                    xAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }}
          />
        </div>
      </div>
    )
  }
}

class RecentUsersGraph extends React.Component {
  constructor(props) {
    super(props);
    this.state = this.initialState;

    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  initialState = {
      name: '',
      surname: '',
      username: '',
      email: '',
      password: '',
      role: 'admin',
      type: 'admin',
      error: ''
  }

}

export default Statistics;
