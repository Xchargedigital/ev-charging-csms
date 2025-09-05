import { ConfigProvider } from 'antd';
import zhCN from 'antd/locale/zh_CN';
import React from 'react';
import { Provider } from 'react-redux';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css';
import Layout from './components/Layout';
import ChargingStations from './pages/ChargingStations';
import Home from './pages/Home';
import Login from './pages/Login';
import Profile from './pages/Profile';
import Register from './pages/Register';
import { store } from './store';

const App: React.FC = () => {
    return (
        <Provider store={store}>
            <ConfigProvider locale={zhCN}>
                <Router>
                    <div className="App">
                        <Routes>
                            <Route path="/login" element={<Login />} />
                            <Route path="/register" element={<Register />} />
                            <Route path="/" element={<Layout />}>
                                <Route index element={<Home />} />
                                <Route path="charging-stations" element={<ChargingStations />} />
                                <Route path="profile" element={<Profile />} />
                            </Route>
                        </Routes>
                    </div>
                </Router>
            </ConfigProvider>
        </Provider>
    );
};

export default App;
