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
          <div className="col-6 pb-3">
            <RecentUsersGraph />
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
      backgroundColor: 'rgba(0,204,0,0.3)',
      borderColor: 'rgba(0,204,0,1)',
      borderWidth: 1,
      hoverBackgroundColor: 'rgba(0,204,0,0.5)',
      hoverBorderColor: 'rgba(0,204,0,1)',
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
    this.state = {
      interval: 'year',
      count: 0
    };

    this.handleIntervalChange = this.handleIntervalChange.bind(this);
  }

  handleIntervalChange(event) {
    const interval = event.target.value
    ApiConnection.countRecent(interval, (apiData) => {
      this.setState({
        interval: interval,
        count: apiData
      });
    });
    event.preventDefault();
  }

  componentDidMount() {
    ApiConnection.countRecent(this.state.interval, apiData => this.setState({count: apiData}));
  }

  render() {
    return (
      <div className="card border-info mb-3" style={{'height': '100%'}}>
        <div className="card-header pb-1">
          <div className="row">
            <div className="col-6">
              Count of recent users for the chosen interval
            </div>
            <div className="col-2 offset-4">
              <select name="interval" value={this.state.interval} onChange={this.handleIntervalChange} className="custom-select custom-select-sm mx-3">
                <option value="week">Week</option>
                <option value="month">Month</option>
                <option value="year">Year</option>
              </select>
            </div>
          </div>
        </div>
        <div className="card-body">
            <div className="card bg-info text-white" style={{'height': '100%'}}>
              <div className="card-body text-center">
                <p style={{'fontSize': '600%', 'position': 'relative',
                   'top': '50%','transform': 'translateY(-50%)'}}>
                   { this.state.count }
                </p>
              </div>
            </div>
        </div>
      </div>
    )
  }

}

export default Statistics;
