
• This contains creation of a simple decentralized application where an organizer can sell tickets with minimum price of his/her will and can later update the ticket price depending on the time, demand etc.
• So there are two part involved in this just like any other application. One being the front-end and second being the back-end.
• Here for front-end I used simple html, css. Although we can use bootstrap or visual studio etc. I kept the front end pretty minimalistic with basic text boxes and buttons.
• The back-end I used Ethereum test network, Rinkeby. I used MetaMask extension. Web3 library for connecting the frontend and backend and then to interact with blockchain.
Process :
• The Ethereum TestRPC is a Node.js Ethereum client for the testing and developing smart contracts. Because it's based on Node.js, we need Node.js installed along with NPM (Node Package Manager) to install it.
• Open up your command line or console and run the following 2 commands:
• > node -v
• > npm -v
• Created a directory as Music_tickets and change it to current directory.
• >Mkdir Music_tickets
• >cd Music_tickets
    
 • Created a directory as SmartContracts under Exhibition directory and changed it to current directory.
• >mkdir SmartContracts
• >cd SmartContracts
• Create a package.json
• >npm init
• Install web3.js beta version (1.0.0-beta.26).
• >npm install --save web3@1.0.0-beta.26
• If either of these commands go unrecognized, visit Nodejs.org and download the appropriate installer. Run it through all of the default options.
• Once finished, close and reload your console and re-run the commands above. They should now provide you with version numbers.
• Next, let's use NPM to install the Ethereumjs-testrpc:
• >npm install -g ethereumjs-testrpc
• There are many ways to do the testing I could have used the testrpc but I used Metamask and Rinkeby test network in it.
• I created a smart contract “fueledMusicTicket.sol”. This file contract in it.
• Then I created the front-end page using html - “main(fueledMusic).html”.
• In the <script> tags of “main(fueledMusic).html” file contains the main code for interacting with contract in blockchain.
• First we import web3.js library. Then define web3.
• Since we are using the chrome extension metamask, the provider is automatically injected, so I commented that line of code In the file.
• Next we are taking the current account address in our Metamask and pre-populating it in the front end.
• Next, we need to use the web3.eth.contract() method to initiatlize (or create) the contract on an address. It accepts one parameter, which is referred to as the ABI (Application Binary Interface).
• This ABI allows you to call functions and receive data from your smart contract.
• If you switch back to the Remix IDE, click on the Compile tab and click Details. Scroll down until you see the Interface - ABI section and click the copy icon

• Now that we have the interface for interacting with our contract through the dappTicket variable, the last thing to do is to define the actual contract address.
• We used Remix to create the contract earlier, and it has an associated address.
• Now I haven’t mentioned this before, we can deploy the contract on to the Metamask address by using the injected web3 option present in environment in run tab of remix IDE.
• Now I pasted the abi here -
• >var ticketcontract = web3.eth.contract(“PASTED THE ABI HERE”)
• Finally called the functions with respect to the buttons I created.
• Now to run the dapp I opened the localhost “main(fueledMusic).html” in chrome where Metamask is on.
