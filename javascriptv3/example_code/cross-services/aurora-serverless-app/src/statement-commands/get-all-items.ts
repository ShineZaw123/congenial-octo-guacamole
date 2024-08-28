// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: Apache-2.0
import { buildStatementCommand } from "./command-helper.js";

const command = buildStatementCommand("select * from items");

export { command };
