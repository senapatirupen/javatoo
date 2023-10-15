In Angular, you can manage environments to handle different configurations and settings for different deployment environments (e.g., development, production). Here's how you can manage environments in Angular:

1. Angular CLI Configuration Files: Angular provides configuration files specific to different environments. By default, you'll have an `environment.ts` file for development and an `environment.prod.ts` file for production. These files are located in the `src/environments` directory.

2. Define Environment Variables: Open the `environment.ts` file and `environment.prod.ts` file and define your environment-specific variables. For example, you can define API endpoints, feature flags, or any other configuration settings relevant to each environment. The variables should be exported as properties of the `environment` object. Here's an example:

```typescript
export const environment = {
  production: false,
  apiUrl: 'https://api.example.com',
  // Add more environment-specific variables as needed
};
```

3. Access Environment Variables: To access the environment variables in your Angular code, import the `environment` object from the appropriate environment file. For example, if you want to access the API URL, import it in your component or service file like this:

```typescript
import { environment } from '../../environments/environment';

// Access the environment variable
const apiUrl = environment.apiUrl;
```

4. Build for Specific Environment: To build your Angular application for a specific environment, use the `--configuration` or `-c` flag with the Angular CLI build command. For example, to build for production, use the following command:

```shell
ng build --configuration=production
```

This will use the `environment.prod.ts` file and replace the environment-specific variables defined in that file during the build process.

5. Angular CLI Configuration: You can customize the Angular CLI configuration in the `angular.json` file. In the `configurations` section, you can define additional environment-specific configurations, such as `staging` or `qa`. This allows you to define custom environment files and build targets. Here's an example:

```json
"configurations": {
  "production": {
    "fileReplacements": [
      {
        "replace": "src/environments/environment.ts",
        "with": "src/environments/environment.prod.ts"
      }
    ],
    "optimization": true,
    // Other production-specific configuration options
  },
  "staging": {
    "fileReplacements": [
      {
        "replace": "src/environments/environment.ts",
        "with": "src/environments/environment.staging.ts"
      }
    ],
    // Other staging-specific configuration options
  }
}
```

By defining different configurations in the `angular.json` file, you can build your Angular application for different environments using the appropriate environment files and configurations.

Managing environments in Angular allows you to handle environment-specific configurations and settings, making it easier to switch between different deployment environments and maintain consistency across different environments.