// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: Apache-2.0
interface AppEnv extends Window {
  APP_ENV: {
    COGNITO_SIGN_IN_URL: string;
    COGNITO_SIGN_OUT_URL: string;
  };
}

export const { COGNITO_SIGN_IN_URL, COGNITO_SIGN_OUT_URL } = (
  window as unknown as AppEnv
).APP_ENV;
