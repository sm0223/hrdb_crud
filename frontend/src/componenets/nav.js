import React from "react";

function BasicNavbar({handleDashboard}) {
    return (
        <nav className="navbar navbar-light bg-light ">
            <div className="container-fluid">
                <a className="navbar-brand" href="/">
                    <b>IIITB Academia</b>
                </a>
                <button onClick={()=>handleDashboard('dashboard')} className="btn btn-outline-danger m-3" >Home</button>
            </div>
        </nav>
    );
}

export default BasicNavbar;