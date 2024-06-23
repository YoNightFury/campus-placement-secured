import React from "react";
import "./Button.css";
import { Link } from "react-router-dom";

export function SigninButton() {
  console.log("in sign-in button");
  return (
    <Link to="sign-in">
      <button className="btn">Sign in!</button>
    </Link>
  );
}
