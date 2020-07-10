import React, {Component} from 'react';

class id extends Component {
    constructor() {
        super();
    }

    state = {
        pathname:''
    }

    componentDidMount() {
        const pathname = this.props.location.pathname;
        console.log(pathname)
        this.setState({
            pathname: pathname
        })
    }

    render() {
        const id = this.props.location.pathname;
        return (
            <div>路由：{this.state.pathname}</div>
        );
    }
}

export default id;
